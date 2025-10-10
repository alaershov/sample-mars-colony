package com.alaershov.mars_colony.habitat.di

import com.alaershov.mars_colony.habitat.HabitatRepository
import com.yandex.yatagan.Module
import com.yandex.yatagan.Provides
import javax.inject.Singleton

@Module
class HabitatRepositoryDiModule {

    @Singleton
    @Provides
    fun provideHabitatRepository(): HabitatRepository {
        return HabitatRepository
    }
}
