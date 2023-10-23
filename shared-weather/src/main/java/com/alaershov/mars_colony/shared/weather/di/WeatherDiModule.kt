package com.alaershov.mars_colony.shared.weather.di

import com.alaershov.mars_colony.shared.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class WeatherDiModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository()
    }
}
