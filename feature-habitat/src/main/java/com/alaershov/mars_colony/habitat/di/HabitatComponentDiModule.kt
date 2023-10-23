package com.alaershov.mars_colony.habitat.di

import com.alaershov.mars_colony.habitat.build_dialog.DefaultHabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.DefaultHabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.info_screen.DefaultHabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.DefaultHabitatListScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.HabitatListScreenComponent
import dagger.Binds
import dagger.Module

@Module
interface HabitatComponentDiModule {

    @Binds
    fun habitatBuildDialogComponentFactory(
        impl: DefaultHabitatBuildDialogComponent.Factory
    ): HabitatBuildDialogComponent.Factory

    @Binds
    fun habitatDismantleDialogComponentFactory(
        impl: DefaultHabitatDismantleDialogComponent.Factory
    ): HabitatDismantleDialogComponent.Factory

    @Binds
    fun habitatInfoScreenComponentFactory(
        impl: DefaultHabitatInfoScreenComponent.Factory
    ): HabitatInfoScreenComponent.Factory

    @Binds
    fun habitatListScreenComponentFactory(
        impl: DefaultHabitatListScreenComponent.Factory
    ): HabitatListScreenComponent.Factory
}
