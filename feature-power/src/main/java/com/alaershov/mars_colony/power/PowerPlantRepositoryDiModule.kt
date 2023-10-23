package com.alaershov.mars_colony.power

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PowerPlantRepositoryDiModule {

    @Singleton
    @Provides
    fun providePowerPlantRepository(): PowerPlantRepository {
        return PowerPlantRepository
    }
}
