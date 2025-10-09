package com.alaershov.mars_colony.shared.weather.di

import com.alaershov.mars_colony.shared.weather.WeatherRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val weatherKoinModule: Module = module {
    single { WeatherRepository() }
}
