package com.alaershov.mars_colony.bottom_sheet

import kotlinx.coroutines.flow.StateFlow

/**
 * Общий интерфейс для компонентов модальных Bottom Sheet.
 */
interface BottomSheetContentComponent {

    val bottomSheetContentState: StateFlow<BottomSheetContentState>
}
