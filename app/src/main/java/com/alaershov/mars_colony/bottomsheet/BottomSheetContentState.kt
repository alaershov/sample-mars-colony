package com.alaershov.mars_colony.bottomsheet

/**
 * Состояние компонента Modal Bottom Sheet
 */
interface BottomSheetContentState {

    /**
     * Разрешено ли сворачивание этого Bottom Sheet.
     */
    val isDismissAllowed: Boolean
}
