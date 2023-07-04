package com.alaershov.bottomsheet_decompose_sample.bottomsheet

import kotlinx.coroutines.flow.StateFlow

/**
 * Общий интерфейс для компонентов модальных Bottom Sheet.
 */
interface BottomSheetContentComponent {

    val bottomSheetContentState: StateFlow<BottomSheetContentState>

    fun onDismissClicked()
}
