package com.alaershov.bottomsheet_decompose_sample.bottomsheet

/**
 * Состояние компонента Modal Bottom Sheet
 */
data class BottomSheetContentState(
    /**
     * Разрешено ли сворачивание этого Bottom Sheet.
     */
    val isDismissAllowed: Boolean,
)
