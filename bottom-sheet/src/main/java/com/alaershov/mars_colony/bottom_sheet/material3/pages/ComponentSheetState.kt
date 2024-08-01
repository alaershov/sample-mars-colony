package com.alaershov.mars_colony.bottom_sheet.material3.pages

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent

/**
 * State Holder для ComponentModalBottomSheet.
 *
 * Содержит одновременно логический стейт компонента `BottomSheetContentComponent`, и
 * UI-стейт `SheetState`. Это позволяет хранить список таких парных стейтов,
 * и использовать его при реализации стека BottomSheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Stable
internal class ComponentSheetState(
    initialComponent: BottomSheetContentComponent?,
) {
    private val _componentState: MutableState<BottomSheetContentComponent?> = mutableStateOf(initialComponent)
    val componentState: State<BottomSheetContentComponent?> = _componentState

    val sheetState: SheetState = SheetState(
        skipPartiallyExpanded = true,
        initialValue = SheetValue.Hidden,
        confirmValueChange = { sheetValue ->
            if (sheetValue == SheetValue.Hidden) {
                val instance = componentState.value
                instance?.bottomSheetContentState?.value?.isDismissAllowed ?: true
            } else {
                true
            }
        },
        skipHiddenState = false,
    )

    fun updateComponent(component: BottomSheetContentComponent?) {
        if (_componentState.value == component) return

        _componentState.value = component
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ComponentSheetState

        if (componentState != other.componentState) return false
        return sheetState == other.sheetState
    }

    override fun hashCode(): Int {
        var result = componentState.hashCode()
        result = 31 * result + sheetState.hashCode()
        return result
    }

    override fun toString(): String {
        return "${this::class.simpleName}@${Integer.toHexString(hashCode())}" +
                "(componentState=$componentState, " +
                "sheetState=$sheetState)"
    }
}
