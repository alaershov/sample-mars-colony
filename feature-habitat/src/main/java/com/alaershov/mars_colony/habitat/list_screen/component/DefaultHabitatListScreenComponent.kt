package com.alaershov.mars_colony.habitat.list_screen.component

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.bottomSheetPages
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.pop
import com.alaershov.mars_colony.bottom_sheet.material3.pages.navigation.pushNew
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.bottom_sheet.HabitatBottomSheetConfig
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.component.HabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreenState
import com.alaershov.mars_colony.habitat.totalCapacity
import com.alaershov.mars_colony.message_dialog.MessageDialogState
import com.alaershov.mars_colony.message_dialog.component.MessageDialogComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.pages.PagesNavigation
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DefaultHabitatListScreenComponent internal constructor(
    componentContext: ComponentContext,
    private val onBackClick: () -> Unit,
    habitatRepository: HabitatRepository,
    private val habitatBuildDialogComponentFactory: HabitatBuildDialogComponent.Factory,
    private val habitatDismantleDialogComponentFactory: HabitatDismantleDialogComponent.Factory,
    private val messageDialogComponentFactory: MessageDialogComponent.Factory,
) : HabitatListScreenComponent, ComponentContext by componentContext {

    private val _state = MutableStateFlow(
        HabitatListScreenState(
            list = emptyList(),
            totalCapacity = 0
        )
    )

    override val state: StateFlow<HabitatListScreenState> = _state

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val bottomSheetPagesNavigation = PagesNavigation<HabitatBottomSheetConfig>()

    override val bottomSheetPages: Value<ChildPages<HabitatBottomSheetConfig, BottomSheetContentComponent>> =
        bottomSheetPages(
            source = bottomSheetPagesNavigation,
            serializer = HabitatBottomSheetConfig.serializer(),
            childFactory = ::createBottomSheet,
        )

    init {
        habitatRepository.state
            .onEach { habitatState ->
                _state.value = HabitatListScreenState(
                    list = habitatState.habitatList,
                    totalCapacity = habitatState.totalCapacity
                )
            }
            .launchIn(scope)
    }

    private fun createBottomSheet(
        config: HabitatBottomSheetConfig,
        componentContext: ComponentContext
    ): BottomSheetContentComponent {
        return when (config) {
            HabitatBottomSheetConfig.HabitatBuild -> {
                habitatBuildDialogComponentFactory.create(
                    componentContext = componentContext,
                    onDismiss = ::dismissBottomSheet,
                )
            }

            is HabitatBottomSheetConfig.HabitatDismantle -> {
                habitatDismantleDialogComponentFactory.create(
                    componentContext = componentContext,
                    habitatId = config.habitatId,
                    onConfirmationNeeded = {
                        bottomSheetPagesNavigation.pushNew(HabitatBottomSheetConfig.ConfirmDismantle)
                    },
                    onDismiss = ::dismissBottomSheet,
                )
            }

            is HabitatBottomSheetConfig.ConfirmDismantle -> {
                messageDialogComponentFactory.create(
                    componentContext = componentContext,
                    dialogState = MessageDialogState(
                        message = "Are you sure?",
                        button = "Yes, dismantle!"
                    ),
                    onButtonClick = {
                        dismissBottomSheet()
                        confirmDismantle()
                    },
                )
            }
        }
    }

    private fun confirmDismantle() {
        val dismantleDialogComponent = bottomSheetPages.value.items
            .map { it.instance }
            .findLast {
                it is HabitatDismantleDialogComponent
            } as? HabitatDismantleDialogComponent

        dismantleDialogComponent?.confirm()
    }

    override fun onBackClick() {
        onBackClick.invoke()
    }

    override fun onBuildClick() {
        bottomSheetPagesNavigation.pushNew(HabitatBottomSheetConfig.HabitatBuild)
    }

    override fun onHabitatClick(id: String) {
        bottomSheetPagesNavigation.pushNew(HabitatBottomSheetConfig.HabitatDismantle(id))
    }

    override fun onBottomSheetPagesDismiss() {
        dismissBottomSheet()
    }

    private fun dismissBottomSheet() {
        bottomSheetPagesNavigation.pop()
    }
}
