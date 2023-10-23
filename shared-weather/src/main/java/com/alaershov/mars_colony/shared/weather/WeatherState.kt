package com.alaershov.mars_colony.shared.weather

data class WeatherState(
    /**
     * Celsius Degrees
     */
    val temperature: Double,

    /**
     * Meters per second
     */
    val windSpeed: Double,

    /**
     * Watts per square meter
     */
    val solarPower: Double,
)
