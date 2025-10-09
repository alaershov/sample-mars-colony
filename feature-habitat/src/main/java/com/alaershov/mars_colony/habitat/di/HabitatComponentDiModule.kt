package com.alaershov.mars_colony.habitat.di

import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.build_dialog.DefaultHabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.component.DefaultHabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.component.HabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.info_screen.DefaultHabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.component.DefaultHabitatListScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.component.HabitatListScreenComponent
import org.koin.core.module.Module
import org.koin.dsl.module

val habitatKoinModule: Module = module {
    single<HabitatRepository> { HabitatRepository }

    factory<HabitatBuildDialogComponent.Factory> {
        object : HabitatBuildDialogComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
                onDismiss: () -> Unit,
            ): HabitatBuildDialogComponent {
                return DefaultHabitatBuildDialogComponent(
                    componentContext = componentContext,
                    onDismiss = onDismiss,
                    habitatRepository = get(),
                )
            }
        }
    }

    factory<HabitatDismantleDialogComponent.Factory> {
        object : HabitatDismantleDialogComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
                habitatId: String,
                onConfirmationNeeded: () -> Unit,
                onDismiss: () -> Unit,
            ): HabitatDismantleDialogComponent {
                return DefaultHabitatDismantleDialogComponent(
                    componentContext = componentContext,
                    habitatId = habitatId,
                    onConfirmationNeeded = onConfirmationNeeded,
                    onDismiss = onDismiss,
                    habitatRepository = get(),
                )
            }
        }
    }

    factory<HabitatInfoScreenComponent.Factory> {
        object : HabitatInfoScreenComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
                habitatId: String,
            ): HabitatInfoScreenComponent {
                return DefaultHabitatInfoScreenComponent(
                    componentContext = componentContext,
                    habitatId = habitatId,
                )
            }
        }
    }

    factory<HabitatListScreenComponent.Factory> {
        object : HabitatListScreenComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
                onBackClick: () -> Unit,
            ): HabitatListScreenComponent {
                return DefaultHabitatListScreenComponent(
                    componentContext = componentContext,
                    onBackClick = onBackClick,
                    habitatRepository = get(),
                    habitatBuildDialogComponentFactory = get(),
                    habitatDismantleDialogComponentFactory = get(),
                    messageDialogComponentFactory = get(),
                )
            }
        }
    }
}
