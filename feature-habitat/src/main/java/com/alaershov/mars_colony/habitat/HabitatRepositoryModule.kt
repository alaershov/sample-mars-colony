package com.alaershov.mars_colony.habitat

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HabitatRepositoryModule {

    @Singleton
    @Provides
    fun provideHabitatRepository(): HabitatRepository {
        return HabitatRepository
    }
}
