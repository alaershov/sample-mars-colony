package com.alaershov.mars_colony.habitat.dismantle_dialog.component

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialogState
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface HabitatDismantleDialogComponent : BottomSheetContentComponent {

    val state: StateFlow<HabitatDismantleDialogState>

    fun onDismantleClick()

    fun confirm()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            habitatId: String,
            onConfirmationNeeded: () -> Unit,
            onDismiss: () -> Unit,
        ): HabitatDismantleDialogComponent
    }
}
