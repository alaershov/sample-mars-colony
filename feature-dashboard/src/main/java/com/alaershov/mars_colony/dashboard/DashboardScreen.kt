package com.alaershov.mars_colony.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.shared.weather.WeatherState
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DashboardScreen(component: DashboardScreenComponent) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Mars Colony Dashboard",
            style = MaterialTheme.typography.headlineLarge
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    component.onHabitatClick()
                }
                .padding(vertical = 16.dp)
        ) {
            val temperature = "%.2f".format(state.weatherState.temperature)
            val windSpeed = "%.2f".format(state.weatherState.windSpeed)
            val solarPower = "%.2f".format(state.weatherState.solarPower)

            Text(
                text = "Temperature: $temperature C",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "Wind: $windSpeed m/s",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = "Solar Power: $solarPower W/m2",
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    component.onHabitatClick()
                }
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
                .clickable {
                    component.onPowerClick()
                }
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "Total Power",
                style = MaterialTheme.typography.titleLarge,
            )

            Text(
                text = state.totalPower.toString(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

class PreviewDashboardScreenComponent : DashboardScreenComponent {
    override val state: StateFlow<DashboardScreenState> = MutableStateFlow(
        DashboardScreenState(
            totalCapacity = 15,
            totalPower = 25000,
            weatherState = WeatherState(
                temperature = 27.5,
                windSpeed = 4.3,
                solarPower = 107.9,
            )
        )
    )

    override fun onHabitatClick() {}

    override fun onPowerClick() {}
}

@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    MarsColonyTheme {
        DashboardScreen(PreviewDashboardScreenComponent())
    }
}
