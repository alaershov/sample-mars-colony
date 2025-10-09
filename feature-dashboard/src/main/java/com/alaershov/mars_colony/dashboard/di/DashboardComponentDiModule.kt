package com.alaershov.mars_colony.dashboard.di

import com.alaershov.mars_colony.dashboard.component.DashboardScreenComponent
import com.alaershov.mars_colony.dashboard.component.DefaultDashboardScreenComponent
import org.koin.core.module.Module
import org.koin.dsl.module

val dashboardKoinModule: Module = module {
    factory<DashboardScreenComponent.Factory> {
        object : DashboardScreenComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
                navigateToHabitatList: () -> Unit,
                navigateToPowerPlantList: () -> Unit,
            ): DashboardScreenComponent {
                return DefaultDashboardScreenComponent(
                    componentContext = componentContext,
                    navigateToHabitatList = navigateToHabitatList,
                    navigateToPowerPlantList = navigateToPowerPlantList,
                    habitatRepository = get(),
                    powerPlantRepository = get(),
                    weatherRepository = get(),
                )
            }
        }
    }
}
