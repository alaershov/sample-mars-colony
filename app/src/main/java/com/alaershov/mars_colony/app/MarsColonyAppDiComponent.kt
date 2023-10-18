package com.alaershov.mars_colony.app

import com.alaershov.mars_colony.dashboard.di.DashboardComponentModule
import com.alaershov.mars_colony.habitat.HabitatRepositoryModule
import com.alaershov.mars_colony.power.PowerPlantRepositoryModule
import com.alaershov.mars_colony.root.RootComponent
import com.alaershov.mars_colony.root.di.RootComponentModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RootComponentModule::class,
        DashboardComponentModule::class,
        HabitatRepositoryModule::class,
        PowerPlantRepositoryModule::class,
    ]
)
interface MarsColonyAppDiComponent {

    val rootComponentFactory: RootComponent.Factory
}
