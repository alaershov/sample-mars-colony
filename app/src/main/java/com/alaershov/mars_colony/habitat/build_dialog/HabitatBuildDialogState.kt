package com.alaershov.mars_colony.habitat.build_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState

data class HabitatBuildDialogState(
    val capacity: Int,
    val isProgress: Boolean,
) : BottomSheetContentState {

    override val isDismissAllowed: Boolean
        get() {
            return !isProgress
        }
}
