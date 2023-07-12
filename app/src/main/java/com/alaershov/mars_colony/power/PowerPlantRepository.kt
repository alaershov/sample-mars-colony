package com.alaershov.mars_colony.power

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

object PowerPlantRepository {

    private val _state = MutableStateFlow(
        PowerPlantState(
            powerPlantList = emptyList()
        )
    )

    val state: StateFlow<PowerPlantState>
        get() = _state

    fun buildPowerPlant(power: Int) {
        val habitat = PowerPlant(
            id = UUID.randomUUID().toString(),
            power = power
        )
        val value = _state.value
        _state.value = value.copy(
            powerPlantList = value.powerPlantList + habitat
        )
    }
}
