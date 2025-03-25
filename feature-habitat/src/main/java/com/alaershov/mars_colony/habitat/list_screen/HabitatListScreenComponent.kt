package com.alaershov.mars_colony.habitat.list_screen

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

interface HabitatListScreenComponent {

    val state: StateFlow<HabitatListScreenState>

    fun onBackClick()

    fun onBuildClick()

    fun onHabitatClick(id: String)

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            onBackClick: () -> Unit,
            onBuildClick: () -> Unit,
            onDismantleHabitatClick: (String) -> Unit,
        ): HabitatListScreenComponent
    }
}
