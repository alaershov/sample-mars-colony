package com.alaershov.mars_colony.shared.weather

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.cos
import kotlin.math.sin

class WeatherRepository {

    private val medianState = WeatherState(
        temperature = 18.0,
        windSpeed = 4.0,
        solarPower = 107.0,
    )

    private val _state = MutableStateFlow(medianState)

    val state: StateFlow<WeatherState>
        get() = _state

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        flow {
            val start = System.currentTimeMillis()
            while (true) {
                delay(160)
                val diff = System.currentTimeMillis() - start
                emit(diff)
            }
        }.onEach { timeMillis ->
            _state.value = WeatherState(
                temperature = medianState.temperature + 5 * fluctuation(timeMillis),
                windSpeed = (medianState.windSpeed + 10 * fluctuation(timeMillis)).coerceAtLeast(0.0),
                solarPower = (medianState.solarPower + 30 * fluctuation(timeMillis)).coerceAtLeast(0.0),
            )
        }.launchIn(scope)
    }

    private fun fluctuation(timeMillis: Long): Double {
        val x: Double = timeMillis / 100000.0
        return (sin(x) + cos(2 * x) - sin(3 * x) + cos(5 * x) - sin(8 * x)) / 3
    }
}
