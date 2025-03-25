package com.alaershov.mars_colony.power.di

import com.alaershov.mars_colony.power.list_screen.component.DefaultPowerPlantListScreenComponent
import com.alaershov.mars_colony.power.list_screen.component.PowerPlantListScreenComponent
import dagger.Binds
import dagger.Module

@Module
interface PowerComponentDiModule {

    @Binds
    fun powerPlantListScreenComponentFactory(
        impl: DefaultPowerPlantListScreenComponent.Factory
    ): PowerPlantListScreenComponent.Factory
}
