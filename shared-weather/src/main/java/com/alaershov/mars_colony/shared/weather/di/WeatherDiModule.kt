package com.alaershov.mars_colony.shared.weather.di

import com.alaershov.mars_colony.shared.weather.WeatherRepository
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.alaershov.mars_colony.shared.weather")
class WeatherDiModule {

    @Single
    fun weatherRepository(): WeatherRepository = WeatherRepository()
}
