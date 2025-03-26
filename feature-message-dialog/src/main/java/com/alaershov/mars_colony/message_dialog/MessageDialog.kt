package com.alaershov.mars_colony.message_dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.message_dialog.component.MessageDialogComponent
import com.alaershov.mars_colony.message_dialog.component.PreviewMessageDialogComponent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme

@Composable
fun MessageDialog(
    component: MessageDialogComponent,
    modifier: Modifier = Modifier
) {
    val state by component.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {
        Text(
            text = state.message,
            style = MaterialTheme.typography.headlineLarge
        )

        Button(
            onClick = {
                component.onButtonClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(state.button)
        }
    }
}

@Preview(device = "id:pixel_9")
@Composable
private fun HabitatDismantleDialogPreview() {
    MarsColonyTheme {
        MessageDialog(PreviewMessageDialogComponent())
    }
}
