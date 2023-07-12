package com.alaershov.mars_colony.main_screen

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import com.alaershov.mars_colony.confirm_dialog.ConfirmContentComponentImpl
import com.alaershov.mars_colony.info_dialog.InfoContentComponentImpl
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class MainScreenComponentImpl(
    private val componentContext: ComponentContext
) : MainScreenComponent, ComponentContext by componentContext {

    private val backCallback = BackCallback {
        val child = bottomSheet.value.child
        if (child != null && child.instance.bottomSheetContentState.value.isDismissAllowed) {
            bottomSheetNavigation.dismiss()
        }
    }

    // собственное состояние компонента - не связано с BottomSheet
    private val _state = MutableStateFlow(MainScreenState(number = 1553))
    override val state: StateFlow<MainScreenState> = _state

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

    override fun onRandomizeClicked() {
        _state.value = _state.value.copy(number = Random.nextInt())
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
}
