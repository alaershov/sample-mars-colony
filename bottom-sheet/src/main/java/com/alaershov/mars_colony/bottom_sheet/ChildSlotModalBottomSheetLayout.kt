@file:OptIn(ExperimentalMaterialApi::class)

package com.alaershov.mars_colony.bottom_sheet

import android.util.Log
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

/**
 * Обёртка над Material ModalBottomSheetLayout для удобной работы
 * с ChildSlot API из Decompose.
 */
@Composable
fun ChildSlotModalBottomSheetLayout(
    sheetContentSlotState: Value<ChildSlot<*, BottomSheetContentComponent>>,
    sheetContent: @Composable (ColumnScope.(BottomSheetContentComponent) -> Unit),
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetShape: Shape = MaterialTheme.shapes.large,
    sheetElevation: Dp = ModalBottomSheetDefaults.Elevation,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    sheetContentColor: Color = contentColorFor(sheetBackgroundColor),
    scrimColor: Color = ModalBottomSheetDefaults.scrimColor,
    content: @Composable () -> Unit,
) {
    val currentOnDismiss by rememberUpdatedState(onDismiss)

    // Текущее состояние BottomSheet.
    val sheetContentSlot: ChildSlot<*, BottomSheetContentComponent> by sheetContentSlotState.subscribeAsState()

    // Последнее запомненное состояние BottomSheet.
    // В отличие от состояния bottomSheet, мы обновляем это поле вручную.
    // Это нужно для того, чтобы при программном закрытии BottomSheet (navigation.dismiss())
    // содержимое BottomSheet не исчезало моментально из-за того, что bottomSheet.child
    // становится равен null.
    // В переменной latestChildInstance мы сохраняем последний активный Child компонент,
    // и зануляем его только тогда, когда отработает функция modalBottomSheetState.hide(),
    // то есть, скрытие ботомщита будет завершено
    var latestChildInstance: BottomSheetContentComponent? by remember {
        mutableStateOf(
            sheetContentSlot.child?.instance
        )
    }

    // Состояние UI ModalBottomSheet.
    val sheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { state ->
            if (state == ModalBottomSheetValue.Hidden) {
                val instance = sheetContentSlot.child?.instance
                instance?.bottomSheetContentState?.value?.isDismissAllowed ?: true
            } else {
                true
            }
        },
        skipHalfExpanded = false
    )

    // Наблюдение за состоянием слота навигации bottomSheet.
    LaunchedEffect(sheetContentSlot) {
        log("sheetContentSlot effect launch")
        val instance = sheetContentSlot.child?.instance

        log("sheetContentSlot change instance $instance")
        if (instance == null) {
            // Если слот занулился, то нужно сначала скрыть ModalBottomSheet,
            // а потом занулить latestChildInstance, чтобы на UI во время
            // скрытия отображалось содержимое BottomSheet, несмотря на то, что child уже null.

            try {
                log("hide() start")
                sheetState.hide()
                log("hide() finishlatestChildInstance=$latestChildInstance")
            } catch (e: Exception) {
                log("hide() exception latestChildInstance=$latestChildInstance", e)
            } finally {
                withContext(NonCancellable) {
                    latestChildInstance = null
                    log("hide() finally latestChildInstance=null")
                }
            }
            log("latestChildInstance = null (finish hide)")
        } else {
            latestChildInstance = instance
            try {
                log("show() start, latestChildInstance=$instance")
                sheetState.show()
                log("show() finish latestChildInstance=$instance")
            } catch (e: Exception) {
                log("show() exception latestChildInstance=$instance", e)
            }
        }
    }

    // Наблюдение за состоянием UI ModalBottomSheet.
    // Если ModalBottomSheet скрывается, сообщаем об этом компоненту-хосту,
    // чтобы он сделал dismniss, и убрал текущего Child из слота навигации.
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }
            .collect { isVisible ->
                if (!isVisible) {
                    currentOnDismiss()
                }
            }
    }

    val componentSheetContent: @Composable ColumnScope.() -> Unit = {
        val childInstance = latestChildInstance
        if (childInstance != null) {
            sheetContent(childInstance)
        } else {
            // Необходимый костыль для пустого состояния ModalBottomSheet.
            // Если его не сделать, анимация открытия не будет работать, он сразу моментально
            // переключится в открытое состояние.
            Spacer(Modifier.height(1.dp))
        }
    }

    ModalBottomSheetLayout(
        sheetContent = componentSheetContent,
        modifier = modifier,
        sheetState = sheetState,
        sheetShape = sheetShape,
        sheetElevation = sheetElevation,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetContentColor = sheetContentColor,
        scrimColor = scrimColor,
        content = content
    )
}

private fun log(message: String, throwable: Throwable? = null) {
    if (throwable != null) {
        Log.e("ChildSlotMBS", message, throwable)
    } else {
        Log.d("ChildSlotMBS", message)
    }
}
