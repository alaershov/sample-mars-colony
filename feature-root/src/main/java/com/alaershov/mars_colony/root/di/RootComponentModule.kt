package com.alaershov.mars_colony.root.di

import com.alaershov.mars_colony.root.DefaultRootComponent
import com.alaershov.mars_colony.root.RootComponent
import dagger.Binds
import dagger.Module

@Module
interface RootComponentModule {

    @Binds
    fun componentFactory(impl: DefaultRootComponent.Factory): RootComponent.Factory
}
