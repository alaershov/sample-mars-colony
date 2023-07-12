package com.alaershov.mars_colony.habitat.list_screen

import com.alaershov.mars_colony.habitat.Habitat

data class HabitatListScreenState(
    val list: List<Habitat>,
    val totalCapacity: Int,
)
