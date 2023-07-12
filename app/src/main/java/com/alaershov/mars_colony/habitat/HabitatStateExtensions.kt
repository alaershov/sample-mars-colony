package com.alaershov.mars_colony.habitat

val HabitatState.totalCapacity: Int
    get() {
        return habitatList.sumOf { it.capacity }
    }
