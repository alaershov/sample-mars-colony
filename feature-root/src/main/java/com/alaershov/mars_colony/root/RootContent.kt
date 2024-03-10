@file:OptIn(ExperimentalDecomposeApi::class)

package com.alaershov.mars_colony.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.bottom_sheet.material2.ChildSlotModalBottomSheetLayout
import com.alaershov.mars_colony.bottom_sheet.material3.pages.ChildPagesModalBottomSheet
import com.alaershov.mars_colony.dashboard.PreviewDashboardScreenComponent
import com.alaershov.mars_colony.root.RootComponent.Child
import com.alaershov.mars_colony.root.bottom_sheet.RootBottomSheetContent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

@Composable
fun RootContent(component: RootComponent) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Material 2 является обычным layuot, и требует вкладывать в себя контент экрана
        ChildSlotModalBottomSheetLayout(
            sheetContentSlotState = component.bottomSheetSlot,
            sheetContent = { component ->
                RootBottomSheetContent(component)
            },
            onDismiss = component::onBottomSheetSlotDismiss,
            sheetShape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            RootScreenContent(component)
        }

        // Material 3 отобразится поверх всего экрана, и не требует вложенности
        ChildPagesModalBottomSheet(
            sheetContentPagesState = component.bottomSheetPages,
            onDismiss = component::onBottomSheetPagesDismiss
        ) { component ->
            RootBottomSheetContent(component)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MarsColonyTheme {
        RootContent(PreviewRootComponent())
    }
}

internal class PreviewRootComponent : RootComponent {

    override val childStack: Value<ChildStack<Unit, Child>> =
        MutableValue(ChildStack(Unit, Child.Dashboard(PreviewDashboardScreenComponent())))

    override val bottomSheetSlot: Value<ChildSlot<Unit, BottomSheetContentComponent>> =
        MutableValue(ChildSlot())

    override val bottomSheetPages: Value<ChildPages<Unit, BottomSheetContentComponent>> =
        MutableValue(ChildPages())

    override fun onBottomSheetSlotDismiss() {}

    override fun onBottomSheetPagesDismiss() {}
}
