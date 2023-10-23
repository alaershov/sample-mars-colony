package com.alaershov.mars_colony.habitat.di

import com.alaershov.mars_colony.habitat.HabitatRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HabitatRepositoryDiModule {

    @Singleton
    @Provides
    fun provideHabitatRepository(): HabitatRepository {
        return HabitatRepository
    }
}
