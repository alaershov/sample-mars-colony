package com.alaershov.mars_colony.habitat.build_dialog

import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface HabitatBuildDialogComponent : BottomSheetContentComponent {

    val state: StateFlow<HabitatBuildDialogState>

    fun onPlusClick()

    fun onMinusClick()

    fun onBuildClick()

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            onDismiss: () -> Unit,
        ): HabitatBuildDialogComponent
    }
}
