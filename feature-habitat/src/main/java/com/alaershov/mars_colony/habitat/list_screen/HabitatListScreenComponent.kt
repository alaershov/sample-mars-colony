package com.alaershov.mars_colony.habitat.list_screen

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface HabitatListScreenComponent {

    val state: StateFlow<HabitatListScreenState>

    fun onBuildClick()

    fun onHabitatClick(id: String)

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            onBuildClick: () -> Unit,
            onDismantleHabitatClick: (String) -> Unit,
        ): HabitatListScreenComponent
    }
}
