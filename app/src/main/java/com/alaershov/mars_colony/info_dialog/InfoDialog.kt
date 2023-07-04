package com.alaershov.mars_colony.info_dialog

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
fun InfoDialog(component: InfoContentComponent) {
    val state by component.state.collectAsState()
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Info Dialog",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Please read carefully",
                style = MaterialTheme.typography.titleMedium
            )
            state.itemList.forEach { item ->
                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    ),
                    text = item
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                onClick = component::onDismissClicked
            ) {
                Text(
                    text = "Ok, got it"
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .padding(bottom = 8.dp),
                onClick = component::onBuyClicked
            ) {
                Text(
                    text = "Buy!"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InfoDialogPreview() {
    MarsColonyTheme {
        InfoDialog(PreviewInfoContentComponent())
    }
}

internal class PreviewInfoContentComponent : InfoContentComponent {
    override val state: StateFlow<InfoDialogState> = MutableStateFlow(
        InfoDialogState(
            listOf(
                "One",
                "Two",
                "Three"
            )
        )
    )

    override val bottomSheetContentState: StateFlow<BottomSheetContentState> = MutableStateFlow(
        BottomSheetContentState(true)
    )

    override fun onBuyClicked() {
    }

    override fun onDismissClicked() {
    }
}
