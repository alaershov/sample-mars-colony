package com.alaershov.mars_colony.habitat.list_screen

import kotlinx.coroutines.flow.StateFlow

interface HabitatListScreenComponent {

    val state: StateFlow<HabitatListScreenState>

    fun onBuildClick()

    fun onHabitatClick(id: String)
}
