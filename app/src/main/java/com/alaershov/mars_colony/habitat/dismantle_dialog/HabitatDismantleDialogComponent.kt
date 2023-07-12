package com.alaershov.mars_colony.habitat.dismantle_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import kotlinx.coroutines.flow.StateFlow

interface HabitatDismantleDialogComponent : BottomSheetContentComponent {

    val state: StateFlow<HabitatDismantleDialogState>

    fun onConfirmClick()
}
