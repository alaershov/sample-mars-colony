@file:OptIn(ExperimentalMaterial3Api::class)

package com.alaershov.mars_colony.bottom_sheet.material3.slot

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.bottom_sheet.material3.ComponentModalBottomSheet
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value

/**
 * Material 3 ModalBottomSheet для отображения BottomSheetContentComponent.
 *
 * Работает как диалог - отображает Popup поверх всего приложения,
 * и не требует заворачивать в себя остальной контент.
 *
 * Показывает BottomSheet на основе ChildSlot из Decompose навгиации.
 */
@Composable
fun ChildSlotModalBottomSheet(
    sheetContentSlotState: Value<ChildSlot<*, BottomSheetContentComponent>>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    key: String = "ChildSlotMBS",
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = androidx.compose.material3.contentColorFor(containerColor),
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    contentWindowInsets: @Composable () -> WindowInsets = { BottomSheetDefaults.windowInsets },
    content: @Composable (ColumnScope.(BottomSheetContentComponent) -> Unit),
) {
    val sheetContentSlot: ChildSlot<*, BottomSheetContentComponent> by sheetContentSlotState.subscribeAsState()

    val sheetContentComponentState: State<BottomSheetContentComponent?> =
        rememberUpdatedState(sheetContentSlot.child?.instance)

    ComponentModalBottomSheet(
        sheetContentComponentState = sheetContentComponentState,
        onDismiss = onDismiss,
        modifier = modifier,
        key = key,
        shape = shape,
        containerColor = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        scrimColor = scrimColor,
        dragHandle = dragHandle,
        contentWindowInsets = contentWindowInsets,
        properties = ModalBottomSheetDefaults.properties,
        content = content,
    )
}
