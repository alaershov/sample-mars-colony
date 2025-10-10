package com.alaershov.mars_colony.shared.weather.di

import com.alaershov.mars_colony.shared.weather.WeatherRepository
import com.yandex.yatagan.Module
import com.yandex.yatagan.Provides
import javax.inject.Singleton

@Module
class WeatherDiModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository()
    }
}
