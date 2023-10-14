package com.alaershov.mars_colony.habitat.list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.alaershov.mars_colony.habitat.Habitat
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun HabitatListScreen(component: HabitatListScreenComponent) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row {
            Text(
                text = "Habitat List",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    component.onBuildClick()
                }
            ) {
                Text(
                    text = "Build",
                )
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Total Habitat Capacity",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = state.totalCapacity.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            state.list.forEach { habitat ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            component.onHabitatClick(habitat.id)
                        }
                        .padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "Habitat ID: ${habitat.id}",
                        style = MaterialTheme.typography.titleMedium,
                    )

                    Text(
                        text = "Capacity: ${habitat.capacity}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

class PreviewHabitatListScreenComponent : HabitatListScreenComponent {

    override val state: StateFlow<HabitatListScreenState> = MutableStateFlow(
        HabitatListScreenState(
            listOf(
                Habitat(
                    id = "1111-1111-1111-1111",
                    capacity = 40
                ),
                Habitat(
                    id = "2222-2222-2222-2222",
                    capacity = 20
                )
            ),
            60
        )
    )

    override fun onBuildClick() {}

    override fun onHabitatClick(id: String) {}
}

@Preview(showBackground = true)
@Composable
private fun HabitatListScreenPreview() {
    MarsColonyTheme {
        HabitatListScreen(PreviewHabitatListScreenComponent())
    }
}
