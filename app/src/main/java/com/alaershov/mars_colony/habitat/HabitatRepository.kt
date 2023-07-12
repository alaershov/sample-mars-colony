package com.alaershov.mars_colony.habitat

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

object HabitatRepository {

    private val _state = MutableStateFlow(
        HabitatState(
            habitatList = emptyList()
        )
    )

    val state: StateFlow<HabitatState>
        get() = _state

    suspend fun buildHabitat(capacity: Int) {
        delay(2000)
        val habitat = Habitat(
            id = UUID.randomUUID().toString(),
            capacity = capacity
        )
        val value = _state.value
        _state.value = value.copy(
            habitatList = value.habitatList + habitat
        )
    }
}
