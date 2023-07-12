package com.alaershov.mars_colony.info_dialog

import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import kotlinx.coroutines.flow.StateFlow

interface InfoContentComponent : BottomSheetContentComponent {
    val state: StateFlow<InfoDialogState>

    fun onBuyClicked()

    fun onDismissClicked()
}
