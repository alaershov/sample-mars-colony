package com.alaershov.mars_colony.habitat.list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitatListScreen(component: HabitatListScreenComponent) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Habitat List",
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        component.onBackClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        component.onBuildClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Build a Habitat"
                    )
                }
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
                .padding(horizontal = 16.dp)
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

    override fun onBackClick() {}

    override fun onBuildClick() {}

    override fun onHabitatClick(id: String) {}
}

@Preview(device = "id:pixel_9")
@Composable
private fun HabitatListScreenPreview() {
    MarsColonyTheme {
        HabitatListScreen(PreviewHabitatListScreenComponent())
    }
}
