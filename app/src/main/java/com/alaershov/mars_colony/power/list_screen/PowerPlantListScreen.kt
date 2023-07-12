package com.alaershov.mars_colony.power.list_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme

@Composable
fun PowerPlantListScreen(component: PowerPlantListScreenComponent) {
    Text("PowerPlantListScreen")
}

class PreviewPowerPlantListScreenComponent : PowerPlantListScreenComponent

@Preview(showBackground = true)
@Composable
private fun HabitatListScreenPreview() {
    MarsColonyTheme {
        PowerPlantListScreen(PreviewPowerPlantListScreenComponent())
    }
}
