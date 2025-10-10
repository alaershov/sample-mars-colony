package com.alaershov.mars_colony.dashboard.di

import com.alaershov.mars_colony.dashboard.component.DefaultDashboardScreenComponent
import com.alaershov.mars_colony.dashboard.component.DashboardScreenComponent
import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.power.PowerPlantRepository
import com.alaershov.mars_colony.shared.weather.WeatherRepository
import com.arkivanov.decompose.ComponentContext
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.alaershov.mars_colony.dashboard")
class DashboardComponentDiModule {

    @Single
    fun dashboardScreenComponentFactory(
        habitatRepository: HabitatRepository,
        powerPlantRepository: PowerPlantRepository,
        weatherRepository: WeatherRepository,
    ): DashboardScreenComponent.Factory {
        return object : DashboardScreenComponent.Factory {
            override fun create(
                componentContext: ComponentContext,
                navigateToHabitatList: () -> Unit,
                navigateToPowerPlantList: () -> Unit,
            ): DashboardScreenComponent {
                return DefaultDashboardScreenComponent(
                    componentContext = componentContext,
                    navigateToHabitatList = navigateToHabitatList,
                    navigateToPowerPlantList = navigateToPowerPlantList,
                    habitatRepository = habitatRepository,
                    powerPlantRepository = powerPlantRepository,
                    weatherRepository = weatherRepository,
                )
            }
        }
    }
}
