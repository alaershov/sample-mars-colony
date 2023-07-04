package com.alaershov.mars_colony.confirm_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ConfirmDialog(component: ConfirmContentComponent) {
    val state by component.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Confirm Dialog",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
                text = state.question
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                onClick = component::onYesClicked
            ) {
                Text(
                    text = "Yeah, sure!"
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                onClick = component::onNoClicked
            ) {
                Text(
                    text = "No, thanks"
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .padding(bottom = 16.dp),
                onClick = component::onInfoClicked
            ) {
                Text(
                    text = "Info"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoDialogPreview() {
    MarsColonyTheme {
        ConfirmDialog(PreviewConfirmContentComponent())
    }
}

internal class PreviewConfirmContentComponent : ConfirmContentComponent {

    override val state: StateFlow<ConfirmDialogState> = MutableStateFlow(
        ConfirmDialogState("Are you sure you wanna do this?")
    )

    override val bottomSheetContentState: StateFlow<BottomSheetContentState> = MutableStateFlow(
        BottomSheetContentState(true)
    )

    override fun onYesClicked() {
    }

    override fun onNoClicked() {
    }

    override fun onInfoClicked() {
    }

    override fun onDismissClicked() {
    }
}
