package com.alaershov.mars_colony.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.dashboard.component.PreviewDashboardScreenComponent
import com.alaershov.mars_colony.root.RootComponent.Child
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import com.arkivanov.decompose.router.pages.ChildPages
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

@Composable
fun RootScreen(component: RootComponent) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        RootNavigation(component)
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MarsColonyTheme {
        RootScreen(PreviewRootComponent())
    }
}

internal class PreviewRootComponent : RootComponent {

    override val childStack: Value<ChildStack<Unit, Child>> =
        MutableValue(ChildStack(Unit, Child.Dashboard(PreviewDashboardScreenComponent())))

    override val bottomSheetPages: Value<ChildPages<Unit, BottomSheetContentComponent>> =
        MutableValue(ChildPages())

    override fun onBottomSheetPagesDismiss() {}
}
