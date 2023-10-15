package com.alaershov.mars_colony.main_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.bottom_sheet.ChildSlotModalBottomSheetLayout
import com.alaershov.mars_colony.dashboard.PreviewDashboardScreenComponent
import com.alaershov.mars_colony.main_screen.MainScreenComponent.Child
import com.alaershov.mars_colony.main_screen.bottom_sheet.MainScreenBottomSheetContent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

@Composable
fun MainScreen(component: MainScreenComponent) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ChildSlotModalBottomSheetLayout(
            sheetContentSlotState = component.bottomSheet,
            sheetContent = { component ->
                MainScreenBottomSheetContent(component)
            },
            onDismiss = component::onBottomSheetDismiss,
            sheetShape = RoundedCornerShape(
                topStart = 20.dp,
                topEnd = 20.dp
            ),
            sheetBackgroundColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            MainScreenContent(component)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MarsColonyTheme {
        MainScreen(PreviewMainScreenComponent())
    }
}

internal class PreviewMainScreenComponent : MainScreenComponent {

    override val childStack: Value<ChildStack<Unit, Child>> =
        MutableValue(ChildStack(Unit, Child.Dashboard(PreviewDashboardScreenComponent())))

    override val bottomSheet: Value<ChildSlot<Unit, BottomSheetContentComponent>> =
        MutableValue(ChildSlot())

    override fun onBottomSheetDismiss() {}
}
