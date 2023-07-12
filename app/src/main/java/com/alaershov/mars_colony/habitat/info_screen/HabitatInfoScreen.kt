package com.alaershov.mars_colony.habitat.info_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme

@Composable
fun HabitatInfoScreen(component: HabitatInfoScreenComponent) {
    Text("HabitatInfoScreen")
}

class PreviewHabitatInfoScreenComponent : HabitatInfoScreenComponent

@Preview(showBackground = true)
@Composable
private fun HabitatInfoScreenPreview() {
    MarsColonyTheme {
        HabitatInfoScreen(PreviewHabitatInfoScreenComponent())
    }
}
