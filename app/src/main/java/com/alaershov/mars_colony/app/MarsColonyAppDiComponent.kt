package com.alaershov.mars_colony.app

import com.alaershov.mars_colony.root.DefaultRootComponent
import com.alaershov.mars_colony.root.di.RootComponentModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RootComponentModule::class,
    ]
)
interface MarsColonyAppDiComponent {

    val rootComponentFactory: DefaultRootComponent.Factory
}
