package com.alaershov.mars_colony.habitat.dismantle_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState
import com.alaershov.mars_colony.habitat.Habitat
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HabitatDismantleDialog(component: HabitatDismantleDialogComponent) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Dismantle Habitat?",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "ID: ${state.habitat?.id}",
            modifier = Modifier
                .padding(top = 20.dp),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Capacity: ${state.habitat?.capacity}",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Total capacity will go down:\n${state.capacityCurrent} -> ${state.capacityAfterDismantle}",
            modifier = Modifier
                .padding(top = 20.dp)
                .align(CenterHorizontally),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )

        Button(
            onClick = {
                component.onConfirmClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text("Dismantle")
        }
    }
}

class PreviewHabitatDismantleDialogComponent : HabitatDismantleDialogComponent {

    override val state: StateFlow<HabitatDismantleDialogState> = MutableStateFlow(
        HabitatDismantleDialogState(
            habitat = Habitat(
                id = "1111-1111-1111-1111",
                capacity = 15,
            ),
            capacityCurrent = 60,
            capacityAfterDismantle = 45,
        )
    )

    override val bottomSheetContentState: StateFlow<BottomSheetContentState> = state

    override fun onConfirmClick() {}
}

@Preview(showBackground = true)
@Composable
private fun HabitatDismantleDialogPreview() {
    MarsColonyTheme {
        HabitatDismantleDialog(PreviewHabitatDismantleDialogComponent())
    }
}
