@file:OptIn(ExperimentalMaterial3Api::class)

package com.alaershov.mars_colony.power.list_screen

import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.power.list_screen.component.PowerPlantListScreenComponent
import com.alaershov.mars_colony.power.list_screen.component.PreviewPowerPlantListScreenComponent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme

@Composable
fun PowerPlantListScreen(component: PowerPlantListScreenComponent) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Power Plant List",
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        // TODO
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
                        // TODO
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
                text = "Total Power",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "0",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
//            state.list.forEach { powerPlant ->
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable {
//                            component.onPowerPlantClick(powerPlant.id)
//                        }
//                        .padding(vertical = 8.dp)
//                ) {
//                    Text(
//                        text = "Power Plant ID: ${powerPlant.id}",
//                        style = MaterialTheme.typography.titleMedium,
//                    )
//
//                    Text(
//                        text = "Power: ${powerPlant.capacity}",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//                }
//            }
        }
    }
}

@Preview(device = "id:pixel_9")
@Composable
private fun HabitatListScreenPreview() {
    MarsColonyTheme {
        PowerPlantListScreen(PreviewPowerPlantListScreenComponent())
    }
}
