package com.alaershov.mars_colony.power

import com.yandex.yatagan.Module
import com.yandex.yatagan.Provides
import javax.inject.Singleton

@Module
class PowerPlantRepositoryDiModule {

    @Singleton
    @Provides
    fun providePowerPlantRepository(): PowerPlantRepository {
        return PowerPlantRepository
    }
}
