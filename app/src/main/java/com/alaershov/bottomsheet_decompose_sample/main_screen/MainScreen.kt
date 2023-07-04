package com.alaershov.bottomsheet_decompose_sample.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.bottomsheet_decompose_sample.bottomsheet.BottomSheetContentComponent
import com.alaershov.bottomsheet_decompose_sample.bottomsheet.ChildSlotModalBottomSheetLayout
import com.alaershov.bottomsheet_decompose_sample.ui.theme.DecomposeSampleTheme
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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
            sheetBackgroundColor = MaterialTheme.colorScheme.surface
        ) {
            MainScreenContent(component)
        }
    }
}

@Composable
private fun MainScreenContent(
    component: MainScreenComponent,
) {
    val screenState = component.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Number is:\n${screenState.value.number}",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )
        Button(
            onClick = component::onRandomizeClicked
        ) {
            Text(text = "Randomize")
        }
        Button(
            onClick = {
                component.onInfoBottomSheetClicked()
            }
        ) {
            Text(text = "Info Bottom Sheet")
        }
        Button(
            onClick = {
                component.onBuyConfirmBottomSheetClicked()
            }
        ) {
            Text(text = "Confirm Bottom Sheet: Buy")
        }
        Button(
            onClick = {
                component.onLogoutConfirmBottomSheetClicked()
            }
        ) {
            Text(text = "Confirm Bottom Sheet: Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    DecomposeSampleTheme {
        MainScreen(PreviewMainScreenComponent())
    }
}

internal class PreviewMainScreenComponent : MainScreenComponent {

    override val state: StateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState(number = 1553))

    override val bottomSheet: Value<ChildSlot<Unit, BottomSheetContentComponent>> =
        MutableValue(ChildSlot())

    override fun onRandomizeClicked() {
    }

    override fun onInfoBottomSheetClicked() {
    }

    override fun onBuyConfirmBottomSheetClicked() {
    }

    override fun onLogoutConfirmBottomSheetClicked() {
    }

    override fun onBottomSheetDismiss() {
    }
}
