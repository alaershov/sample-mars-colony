package com.alaershov.mars_colony.power.di

import com.alaershov.mars_colony.power.list_screen.DefaultPowerPlantListScreenComponent
import com.alaershov.mars_colony.power.list_screen.PowerPlantListScreenComponent
import dagger.Binds
import dagger.Module

@Module
interface PowerComponentDiModule {

    @Binds
    fun powerPlantListScreenComponentFactory(
        impl: DefaultPowerPlantListScreenComponent.Factory
    ): PowerPlantListScreenComponent.Factory
}
