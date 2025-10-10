package com.alaershov.mars_colony.power.di

import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.power.list_screen.component.DefaultPowerPlantListScreenComponent
import com.alaershov.mars_colony.power.list_screen.component.PowerPlantListScreenComponent
import com.arkivanov.decompose.ComponentContext
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.alaershov.mars_colony.power")
class PowerComponentDiModule {

    @Single
    fun powerPlantRepository(): PowerPlantRepository = PowerPlantRepository

    @Single
    fun powerPlantListScreenComponentFactory(): PowerPlantListScreenComponent.Factory {
        return object : PowerPlantListScreenComponent.Factory {
            override fun create(
                componentContext: ComponentContext,
            ): PowerPlantListScreenComponent {
                return DefaultPowerPlantListScreenComponent(
                    componentContext = componentContext,
                )
            }
        }
    }
}
