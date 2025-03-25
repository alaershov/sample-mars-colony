package com.alaershov.mars_colony.dashboard.di

import com.alaershov.mars_colony.dashboard.component.DashboardScreenComponent
import com.alaershov.mars_colony.dashboard.component.DefaultDashboardScreenComponent
import dagger.Binds
import dagger.Module

@Module
interface DashboardComponentDiModule {

    @Binds
    fun componentFactory(impl: DefaultDashboardScreenComponent.Factory): DashboardScreenComponent.Factory
}
