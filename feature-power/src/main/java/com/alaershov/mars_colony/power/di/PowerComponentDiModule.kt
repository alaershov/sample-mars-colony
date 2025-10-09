package com.alaershov.mars_colony.power.di

import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.list_screen.component.DefaultPowerPlantListScreenComponent
import com.alaershov.mars_colony.power.list_screen.component.PowerPlantListScreenComponent
import org.koin.core.module.Module
import org.koin.dsl.module

val powerKoinModule: Module = module {
    single<PowerPlantRepository> { PowerPlantRepository }

    factory<PowerPlantListScreenComponent.Factory> {
        object : PowerPlantListScreenComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
            ): PowerPlantListScreenComponent {
                return DefaultPowerPlantListScreenComponent(
                    componentContext = componentContext,
                )
            }
        }
    }
}
