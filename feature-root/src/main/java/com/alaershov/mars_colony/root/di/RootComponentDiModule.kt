package com.alaershov.mars_colony.root.di

import com.alaershov.mars_colony.root.DefaultRootComponent
import com.alaershov.mars_colony.root.RootComponent
import org.koin.core.module.Module
import org.koin.dsl.module

val rootKoinModule: Module = module {
    factory<RootComponent.Factory> {
        object : RootComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext
            ): RootComponent {
                return DefaultRootComponent(
                    componentContext = componentContext,
                    dashboardComponentFactory = get(),
                    habitatInfoScreenComponentFactory = get(),
                    habitatListScreenComponentFactory = get(),
                    powerPlantListScreenComponentFactory = get(),
                )
            }
        }
    }
}
