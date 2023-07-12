package com.alaershov.mars_colony.habitat.build_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import kotlinx.coroutines.flow.StateFlow

interface HabitatBuildDialogComponent : BottomSheetContentComponent {

    val state: StateFlow<HabitatBuildDialogState>

    fun onPlusClick()

    fun onMinusClick()

    fun onBuildClick()
}
