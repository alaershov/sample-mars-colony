package com.alaershov.mars_colony.main_screen

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import com.alaershov.mars_colony.confirm_dialog.ConfirmContentComponentImpl
import com.alaershov.mars_colony.dashboard_screen.DashboardScreenComponentImpl
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponentImpl
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponentImpl
import com.alaershov.mars_colony.info_dialog.InfoContentComponentImpl
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreenComponentImpl
import com.alaershov.mars_colony.main_screen.MainScreenComponent.Child
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.list_screen.PowerPlantListScreenComponentImpl
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

class MainScreenComponentImpl(
    private val componentContext: ComponentContext
) : MainScreenComponent, ComponentContext by componentContext {

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
    private val bottomSheetNavigation = SlotNavigation<MainScreenBottomSheetConfig>()

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
            is MainScreenBottomSheetConfig.Confirm -> {
                ConfirmContentComponentImpl(
                    componentContext = componentContext,
                    question = config.question,
                    onDismissed = bottomSheetNavigation::dismiss,
                    onInfoClicked = {
                        bottomSheetNavigation.activate(MainScreenBottomSheetConfig.Info)
                    }
                )
            }

            MainScreenBottomSheetConfig.Info -> {
                InfoContentComponentImpl(
                    componentContext = componentContext,
                    onDismissed = bottomSheetNavigation::dismiss,
                    onBuyClicked = {
                        bottomSheetNavigation.activate(
                            MainScreenBottomSheetConfig.Confirm(
                                question = "Do you want to buy this?"
                            )
                        )
                    }
                )
            }

            MainScreenBottomSheetConfig.HabitatBuild -> {
                HabitatBuildDialogComponentImpl(
                    componentContext = componentContext,
                    habitatRepository = HabitatRepository,
                    onDismissed = bottomSheetNavigation::dismiss,
                )
            }
        }
    }

    init {
        backHandler.register(backCallback)
        bottomSheet.subscribe {
            backCallback.isEnabled = it.child != null
        }
    }

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            Config.Dashboard -> Child.Dashboard(
                DashboardScreenComponentImpl(
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
                HabitatListScreenComponentImpl(
                    componentContext = componentContext,
                    habitatRepository = HabitatRepository,
                    onBuildClick = {
                        bottomSheetNavigation.activate(MainScreenBottomSheetConfig.HabitatBuild)
                    }
                )
            )

            is Config.HabitatInfo -> Child.HabitatInfo(
                HabitatInfoScreenComponentImpl(
                    componentContext = componentContext,
                    habitatId = config.habitatId
                )
            )

            Config.PowerPlantList -> Child.PowerPlantList(
                PowerPlantListScreenComponentImpl(
                    componentContext = componentContext
                )
            )
        }
    }

    override fun onInfoBottomSheetClicked() {
        bottomSheetNavigation.activate(MainScreenBottomSheetConfig.Info)
    }

    override fun onBuyConfirmBottomSheetClicked() {
        bottomSheetNavigation.activate(
            MainScreenBottomSheetConfig.Confirm(
                question = "Do you want to buy this?"
            )
        )
    }

    override fun onLogoutConfirmBottomSheetClicked() {
        bottomSheetNavigation.activate(
            MainScreenBottomSheetConfig.Confirm(
                question = "Log out? Really?"
            )
        )
    }

    override fun onBottomSheetDismiss() {
        bottomSheetNavigation.dismiss()
    }

    private sealed class Config : Parcelable {

        @Parcelize
        object Dashboard : Config()

        @Parcelize
        object HabitatList : Config()

        @Parcelize
        data class HabitatInfo(
            val habitatId: String
        ) : Config()

        @Parcelize
        object PowerPlantList : Config()
    }
}
