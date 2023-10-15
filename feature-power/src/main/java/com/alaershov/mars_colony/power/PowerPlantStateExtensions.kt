package com.alaershov.mars_colony.power

val PowerPlantState.totalPower: Int
    get() {
        return powerPlantList.sumOf { it.power }
    }
