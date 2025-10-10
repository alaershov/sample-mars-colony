package com.alaershov.mars_colony.root.di

import com.alaershov.mars_colony.root.DefaultRootComponent
import com.alaershov.mars_colony.root.RootComponent
import com.yandex.yatagan.Binds
import com.yandex.yatagan.Module

@Module
interface RootComponentDiModule {

    @Binds
    fun componentFactory(impl: DefaultRootComponent.Factory): RootComponent.Factory
}
