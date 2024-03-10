@file:OptIn(ExperimentalDecomposeApi::class)

package com.alaershov.mars_colony.root

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.bottomSheetPages
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.pop
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.pushNew
import com.alaershov.mars_colony.dashboard.DashboardScreenComponent
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreenComponent
import com.alaershov.mars_colony.power.list_screen.PowerPlantListScreenComponent
import com.alaershov.mars_colony.root.RootComponent.Child
import com.alaershov.mars_colony.root.bottom_sheet.RootBottomSheetConfig
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackCallback
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultRootComponent @AssistedInject internal constructor(
    @Assisted
    private val componentContext: ComponentContext,
    @Assisted
    private val isMaterial3BottomSheet: Boolean,

    // Кроме контекста, корневому компоненту нужно уметь создавать свои дочерние компоненты
    // Для этого в конструктор компонента передаются фабрики, с помощью которых можно
    // создать инстансы дочерних компонентов в нужный момент.
    //
    // Нужны только фабрики непосредственно вложенных компонентов
    // Если в каком-то из них будет своя внутренняя навигация, им точно так же нужно
    // будет передать фабрики компонентов, участвующих в этой навигации.
    private val dashboardComponentFactory: DashboardScreenComponent.Factory,
    private val habitatBuildDialogComponentFactory: HabitatBuildDialogComponent.Factory,
    private val habitatDismantleDialogComponentFactory: HabitatDismantleDialogComponent.Factory,
    private val habitatInfoScreenComponentFactory: HabitatInfoScreenComponent.Factory,
    private val habitatListScreenComponentFactory: HabitatListScreenComponent.Factory,
    private val powerPlantListScreenComponentFactory: PowerPlantListScreenComponent.Factory,
) : RootComponent, ComponentContext by componentContext {

    private val backCallback = BackCallback {
        val child = bottomSheetSlot.value.child
        if (child != null && child.instance.bottomSheetContentState.value.isDismissAllowed) {
            bottomSheetSlotNavigation.dismiss()
        }
    }

    private val navigation = StackNavigation<Config>()

    private val _childStack =
        childStack(
            source = navigation,
            initialConfiguration = Config.Dashboard,
            handleBackButton = true, // Pop the back stack on back button press
            childFactory = ::createChild,
        )

    override val childStack: Value<ChildStack<*, Child>> = _childStack

    private val bottomSheetSlotNavigation = SlotNavigation<RootBottomSheetConfig>()

    override val bottomSheetSlot: Value<ChildSlot<*, BottomSheetContentComponent>> =
        childSlot(
            source = bottomSheetSlotNavigation,
            // Не нужно персистентное сосотяние диалогов.
            persistent = false,
            // Не используем стандартную обработку кнопки "Назад".
            // Вместо этого делаем кастомную, с учётом isDismissAllowed.
            handleBackButton = false,
            childFactory = ::createBottomSheet,
        )

    private val bottomSheetPagesNavigation = PagesNavigation<RootBottomSheetConfig>()

    override val bottomSheetPages: Value<ChildPages<RootBottomSheetConfig, BottomSheetContentComponent>> =
        bottomSheetPages(
            source = bottomSheetPagesNavigation,
            childFactory = ::createBottomSheet,
        )

    init {
        backHandler.register(backCallback)
        bottomSheetSlot.observe {
            backCallback.isEnabled = it.child != null
        }
    }

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Dashboard -> Child.Dashboard(
                dashboardComponentFactory.create(
                    componentContext = componentContext,
                    navigateToHabitatList = {
                        navigation.push(Config.HabitatList)
                    },
                    navigateToPowerPlantList = {
                        navigation.push(Config.PowerPlantList)
                    }
                )
            )

            Config.HabitatList -> Child.HabitatList(
                habitatListScreenComponentFactory.create(
                    componentContext = componentContext,
                    onBuildClick = {
                        showBottomSheet(RootBottomSheetConfig.HabitatBuild)
                    },
                    onDismantleHabitatClick = { id ->
                        showBottomSheet(RootBottomSheetConfig.HabitatDismantle(id))
                    }
                )
            )

            is Config.HabitatInfo -> Child.HabitatInfo(
                habitatInfoScreenComponentFactory.create(
                    componentContext = componentContext,
                    habitatId = config.habitatId
                )
            )

            Config.PowerPlantList -> Child.PowerPlantList(
                powerPlantListScreenComponentFactory.create(
                    componentContext = componentContext
                )
            )
        }
    }

    private fun createBottomSheet(
        config: RootBottomSheetConfig,
        componentContext: ComponentContext
    ): BottomSheetContentComponent {
        return when (config) {
            RootBottomSheetConfig.HabitatBuild -> {
                habitatBuildDialogComponentFactory.create(
                    componentContext = componentContext,
                    onDismiss = ::dismissBottomSheet,
                )
            }

            is RootBottomSheetConfig.HabitatDismantle -> {
                habitatDismantleDialogComponentFactory.create(
                    componentContext = componentContext,
                    habitatId = config.habitatId,
                    onDismiss = ::dismissBottomSheet,
                )
            }
        }
    }

    private fun showBottomSheet(config: RootBottomSheetConfig) {
        if (isMaterial3BottomSheet) {
            bottomSheetPagesNavigation.pushNew(config)
        } else {
            bottomSheetSlotNavigation.activate(config)
        }
    }

    private fun dismissBottomSheet() {
        if (isMaterial3BottomSheet) {
            bottomSheetPagesNavigation.pop()
        } else {
            bottomSheetSlotNavigation.dismiss()
        }
    }

    override fun onBottomSheetSlotDismiss() {
        bottomSheetSlotNavigation.dismiss()
    }

    override fun onBottomSheetPagesDismiss() {
        bottomSheetPagesNavigation.pop()
    }

    private sealed class Config : Parcelable {

        @Parcelize
        data object Dashboard : Config()

        @Parcelize
        data object HabitatList : Config()

        @Parcelize
        data class HabitatInfo(
            val habitatId: String
        ) : Config()

        @Parcelize
        data object PowerPlantList : Config()
    }

    @AssistedFactory
    interface Factory : RootComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
            isMaterial3BottomSheet: Boolean,
        ): DefaultRootComponent
    }
}
