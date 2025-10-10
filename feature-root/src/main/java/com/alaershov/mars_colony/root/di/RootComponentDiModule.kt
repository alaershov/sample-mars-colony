package com.alaershov.mars_colony.root.di

import com.alaershov.mars_colony.dashboard.component.DashboardScreenComponent
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.component.HabitatListScreenComponent
import com.alaershov.mars_colony.power.list_screen.component.PowerPlantListScreenComponent
import com.alaershov.mars_colony.root.DefaultRootComponent
import com.alaershov.mars_colony.root.RootComponent
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.alaershov.mars_colony.root")
class RootComponentDiModule {

    @Single
    fun rootComponentFactory(
        dashboardComponentFactory: DashboardScreenComponent.Factory,
        habitatInfoScreenComponentFactory: HabitatInfoScreenComponent.Factory,
        habitatListScreenComponentFactory: HabitatListScreenComponent.Factory,
        powerPlantListScreenComponentFactory: PowerPlantListScreenComponent.Factory,
    ): RootComponent.Factory {
        return object : RootComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext
            ): RootComponent {
                return DefaultRootComponent(
                    componentContext = componentContext,
                    dashboardComponentFactory = dashboardComponentFactory,
                    habitatInfoScreenComponentFactory = habitatInfoScreenComponentFactory,
                    habitatListScreenComponentFactory = habitatListScreenComponentFactory,
                    powerPlantListScreenComponentFactory = powerPlantListScreenComponentFactory,
                )
            }
        }
    }
}
