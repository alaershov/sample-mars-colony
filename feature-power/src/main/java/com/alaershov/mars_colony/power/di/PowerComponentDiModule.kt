package com.alaershov.mars_colony.power.di

import com.alaershov.mars_colony.power.list_screen.component.DefaultPowerPlantListScreenComponent
import com.alaershov.mars_colony.power.list_screen.component.PowerPlantListScreenComponent
import com.yandex.yatagan.Binds
import com.yandex.yatagan.Module

@Module
interface PowerComponentDiModule {

    @Binds
    fun powerPlantListScreenComponentFactory(
        impl: DefaultPowerPlantListScreenComponent.Factory
    ): PowerPlantListScreenComponent.Factory
}
