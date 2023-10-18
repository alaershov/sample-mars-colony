package com.alaershov.mars_colony.root

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.dashboard.DefaultDashboardScreenComponent
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.build_dialog.DefaultHabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.DefaultHabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.info_screen.DefaultHabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.DefaultHabitatListScreenComponent
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.list_screen.DefaultPowerPlantListScreenComponent
import com.alaershov.mars_colony.root.RootComponent.Child
import com.alaershov.mars_colony.root.bottom_sheet.RootBottomSheetConfig
import com.arkivanov.decompose.ComponentContext
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

class DefaultRootComponent @AssistedInject constructor(
    @Assisted
    private val componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val backCallback = BackCallback {
        val child = bottomSheet.value.child
        if (child != null && child.instance.bottomSheetContentState.value.isDismissAllowed) {
            bottomSheetNavigation.dismiss()
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

    // интерфейс для запуска действий навигации в слоте BottomSheet
    private val bottomSheetNavigation = SlotNavigation<RootBottomSheetConfig>()

    // состояние слота BottomSheet - используется для отрисовки на экране
    override val bottomSheet: Value<ChildSlot<*, BottomSheetContentComponent>> = childSlot(
        source = bottomSheetNavigation,
        // Не нужно персистентное сосотяние диалогов.
        persistent = false,
        // Не используем стандартную обработку кнопки "Назад".
        // Вместо этого делаем кастомную, с учётом isDismissAllowed.
        handleBackButton = false,
    ) { config, componentContext ->
        when (config) {
            RootBottomSheetConfig.HabitatBuild -> {
                DefaultHabitatBuildDialogComponent(
                    componentContext = componentContext,
                    habitatRepository = HabitatRepository,
                    onDismissed = bottomSheetNavigation::dismiss,
                )
            }

            is RootBottomSheetConfig.HabitatDismantle -> {
                DefaultHabitatDismantleDialogComponent(
                    componentContext = componentContext,
                    habitatId = config.habitatId,
                    habitatRepository = HabitatRepository,
                    onDismiss = bottomSheetNavigation::dismiss,
                )
            }
        }
    }

    init {
        backHandler.register(backCallback)
        bottomSheet.observe {
            backCallback.isEnabled = it.child != null
        }
    }

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Dashboard -> Child.Dashboard(
                DefaultDashboardScreenComponent(
                    componentContext = componentContext,
                    habitatRepository = HabitatRepository,
                    powerPlantRepository = PowerPlantRepository,
                    navigateToHabitatList = {
                        navigation.push(Config.HabitatList)
                    },
                    navigateToPowerPlantList = {
                        navigation.push(Config.PowerPlantList)
                    }
                )
            )

            Config.HabitatList -> Child.HabitatList(
                DefaultHabitatListScreenComponent(
                    componentContext = componentContext,
                    habitatRepository = HabitatRepository,
                    onBuildClick = {
                        bottomSheetNavigation.activate(
                            RootBottomSheetConfig.HabitatBuild
                        )
                    },
                    onDismantleHabitatClick = { id ->
                        bottomSheetNavigation.activate(
                            RootBottomSheetConfig.HabitatDismantle(id)
                        )
                    }
                )
            )

            is Config.HabitatInfo -> Child.HabitatInfo(
                DefaultHabitatInfoScreenComponent(
                    componentContext = componentContext,
                    habitatId = config.habitatId
                )
            )

            Config.PowerPlantList -> Child.PowerPlantList(
                DefaultPowerPlantListScreenComponent(
                    componentContext = componentContext
                )
            )
        }
    }

    override fun onBottomSheetDismiss() {
        bottomSheetNavigation.dismiss()
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
        ): DefaultRootComponent
    }
}
