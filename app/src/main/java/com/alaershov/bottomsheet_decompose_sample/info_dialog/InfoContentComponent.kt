package com.alaershov.bottomsheet_decompose_sample.info_dialog

import com.alaershov.bottomsheet_decompose_sample.bottomsheet.BottomSheetContentComponent
import kotlinx.coroutines.flow.StateFlow

interface InfoContentComponent : BottomSheetContentComponent {
    val state: StateFlow<InfoDialogState>

    fun onBuyClicked()
}
