package com.alaershov.mars_colony.habitat.build_dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.bottomsheet.BottomSheetContentState
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HabitatBuildDialog(component: HabitatBuildDialogComponent) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Build Habitat",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Capacity",
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 40.dp),
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier.align(CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    component.onMinusClick()
                }
            ) {
                Text(
                    text = "-",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Text(
                text = state.capacity.toString(),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Button(
                onClick = {
                    component.onPlusClick()
                }
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
        Button(
            onClick = {
                component.onBuildClick()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            if (state.isProgress) {
                CircularProgressIndicator(
                    color = Color.Black,
                    strokeWidth = 2.dp,
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Text("Build")
            }
        }
    }
}

class PreviewHabitatBuildDialogComponent : HabitatBuildDialogComponent {
    override val state: StateFlow<HabitatBuildDialogState> = MutableStateFlow(
        HabitatBuildDialogState(
            capacity = 15,
            isProgress = true
        )
    )

    override val bottomSheetContentState: StateFlow<BottomSheetContentState> = state

    override fun onPlusClick() {}

    override fun onMinusClick() {}

    override fun onBuildClick() {}
}

@Preview(showBackground = true)
@Composable
private fun HabitatBuildDialogPreview() {
    MarsColonyTheme {
        HabitatBuildDialog(PreviewHabitatBuildDialogComponent())
    }
}
