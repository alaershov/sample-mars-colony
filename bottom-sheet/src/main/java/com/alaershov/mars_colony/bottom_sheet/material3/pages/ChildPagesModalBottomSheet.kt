@file:OptIn(ExperimentalDecomposeApi::class, ExperimentalMaterial3Api::class)

package com.alaershov.mars_colony.bottom_sheet.material3.pages

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.bottom_sheet.material3.ComponentModalBottomSheet
import com.alaershov.mars_colony.bottom_sheet.material3.bottomSheetWindowInsets
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.value.Value

/**
 * Material 3 ModalBottomSheet для отображения стека BottomSheetContentComponent.
 *
 * Работает как диалог - отображает Popup поверх всего приложения,
 * и не требует заворачивать в себя остальной контент.
 *
 * Показывает множественные BottomSheet друг над другом,
 * на основе ChildPages из Decompose навигиации.
 */
@Composable
fun ChildPagesModalBottomSheet(
    sheetContentPagesState: Value<ChildPages<*, BottomSheetContentComponent>>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    key: String = "ChildPagesMBS",
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = androidx.compose.material3.contentColorFor(containerColor),
    tonalElevation: Dp = BottomSheetDefaults.Elevation,
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    dragHandle: @Composable (() -> Unit)? = { BottomSheetDefaults.DragHandle() },
    windowInsets: WindowInsets = bottomSheetWindowInsets(),
    content: @Composable (ColumnScope.(BottomSheetContentComponent) -> Unit),
) {
    // наблюдаем за состоянияем ChildPages
    // при каждом изменении стека навигации страниц - добавлении или удалении - значение обновится.
    val sheetContentStack: ChildPages<*, BottomSheetContentComponent> by sheetContentPagesState.subscribeAsState()

    // инициализируем и запоминаем состояние всех BottomSheet в этом стеке навигации
    val state = remember(sheetContentPagesState) {
        ComponentPagesSheetState()
    }
    // обновляем это состояние при каждом изменении стека навигации
    LaunchedEffect(sheetContentStack) {
        state.update(sheetContentStack)
    }

    // state содержит актуальное и обновлённое состояние для отображения всех BottomSheet,
    // поэтому просто рисуем их друг за другом в списке. Лишних рекомпозиций предыдущих элементов списка
    // не должно происходить, потому что их состояния запомнены в этом списке, и не меняются при добавлении
    // или удалении страниц сверху стека.
    state.componentSheetStateListState.value.forEach { componentSheetState ->
        ComponentModalBottomSheet(
            sheetContentComponentState = componentSheetState.componentState,
            sheetState = componentSheetState.sheetState,
            onDismiss = {
                if (componentSheetState.componentState.value != null) {
                    // Инстанс компонента будет не null в этот момент,
                    // если dismiss был вызыван со стороны UI.
                    // В этом случае компонент надо удалить в стеке навгиации,
                    // для этого вызываем onDismiss()

                    onDismiss()
                } else {
                    // Инстанс компонента будет null в этот момент,
                    // если dismiss был вызыван со стороны логики навгиации.
                    // В этом случае надо ещё раз обновить стек, чтобы убрать
                    // из него верхний стейт с пустым компонентом.

                    state.update(sheetContentStack)
                }
            },
            modifier = modifier,
            key = key,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            tonalElevation = tonalElevation,
            scrimColor = scrimColor,
            dragHandle = dragHandle,
            windowInsets = windowInsets,
            properties = ModalBottomSheetDefaults.properties(),
            content = content,
        )
    }
}
