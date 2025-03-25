@file:OptIn(ExperimentalMaterial3Api::class)

package com.alaershov.mars_colony.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alaershov.mars_colony.dashboard.component.DashboardScreenComponent
import com.alaershov.mars_colony.dashboard.component.PreviewDashboardScreenComponent
import com.alaershov.mars_colony.ui.theme.MarsColonyTheme

@Composable
fun DashboardScreen(component: DashboardScreenComponent) {
    val state by component.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Mars Colony Dashboard",
                    style = MaterialTheme.typography.headlineLarge
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    component.onHabitatClick()
                }
                .padding(16.dp)
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
                .clickable {
                    component.onPowerClick()
                }
                .padding(16.dp)
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

@Preview(device = "id:pixel_9")
@Composable
private fun DashboardScreenPreview() {
    MarsColonyTheme {
        DashboardScreen(PreviewDashboardScreenComponent())
    }
}
