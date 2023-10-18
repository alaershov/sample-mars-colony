package com.alaershov.mars_colony.dashboard.di

import com.alaershov.mars_colony.dashboard.DashboardScreenComponent
import com.alaershov.mars_colony.dashboard.DefaultDashboardScreenComponent
import dagger.Binds
import dagger.Module

@Module
interface DashboardComponentModule {

    @Binds
    fun componentFactory(impl: DefaultDashboardScreenComponent.Factory): DashboardScreenComponent.Factory
}
