package com.alaershov.mars_colony.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.alaershov.mars_colony.dashboard.DashboardScreen
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreen
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreen
import com.alaershov.mars_colony.power.list_screen.PowerPlantListScreen
import com.alaershov.mars_colony.root.RootComponent.Child
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.stack.ChildStack

@Composable
fun RootNavigation(
    component: RootComponent,
) {
    val childStack: ChildStack<*, Child> by component.childStack.subscribeAsState()

    when (val instance = childStack.active.instance) {
        is Child.Dashboard -> {
            DashboardScreen(instance.component)
        }

        is Child.HabitatList -> {
            HabitatListScreen(instance.component)
        }

        is Child.HabitatInfo -> {
            HabitatInfoScreen(instance.component)
        }

        is Child.PowerPlantList -> {
            PowerPlantListScreen(instance.component)
        }
    }
}
