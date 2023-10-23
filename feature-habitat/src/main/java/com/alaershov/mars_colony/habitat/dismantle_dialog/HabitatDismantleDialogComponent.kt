package com.alaershov.mars_colony.habitat.dismantle_dialog

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface HabitatDismantleDialogComponent : BottomSheetContentComponent {

    val state: StateFlow<HabitatDismantleDialogState>

    fun onConfirmClick()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            habitatId: String,
            onDismiss: () -> Unit,
        ): HabitatDismantleDialogComponent
    }
}
