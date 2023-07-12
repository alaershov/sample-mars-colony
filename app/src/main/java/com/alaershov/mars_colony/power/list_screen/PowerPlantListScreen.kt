package com.alaershov.mars_colony.power.list_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme

@Composable
fun PowerPlantListScreen(component: PowerPlantListScreenComponent) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Row {
            Text(
                text = "Power Plant List",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    // TODO
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

class PreviewPowerPlantListScreenComponent : PowerPlantListScreenComponent

@Preview(showBackground = true)
@Composable
private fun HabitatListScreenPreview() {
    MarsColonyTheme {
        PowerPlantListScreen(PreviewPowerPlantListScreenComponent())
    }
}
