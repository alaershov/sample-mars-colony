package com.alaershov.mars_colony.habitat.di

import com.alaershov.mars_colony.habitat.HabitatRepository
import com.alaershov.mars_colony.habitat.build_dialog.DefaultHabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.component.DefaultHabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.component.HabitatDismantleDialogComponent
import com.alaershov.mars_colony.habitat.info_screen.DefaultHabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.info_screen.HabitatInfoScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.component.DefaultHabitatListScreenComponent
import com.alaershov.mars_colony.habitat.list_screen.component.HabitatListScreenComponent
import com.arkivanov.decompose.ComponentContext
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.alaershov.mars_colony.habitat")
class HabitatComponentDiModule {

    @Single
    fun habitatRepository(): HabitatRepository = HabitatRepository

    @Single
    fun habitatBuildDialogComponentFactory(
        habitatRepository: HabitatRepository,
    ): HabitatBuildDialogComponent.Factory {
        return object : HabitatBuildDialogComponent.Factory {
            override fun create(
                componentContext: ComponentContext,
                onDismiss: () -> Unit,
            ): HabitatBuildDialogComponent {
                return DefaultHabitatBuildDialogComponent(
                    componentContext = componentContext,
                    onDismiss = onDismiss,
                    habitatRepository = habitatRepository,
                )
            }
        }
    }

    @Single
    fun habitatDismantleDialogComponentFactory(
        habitatRepository: HabitatRepository,
    ): HabitatDismantleDialogComponent.Factory {
        return object : HabitatDismantleDialogComponent.Factory {
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
                    habitatRepository = habitatRepository,
                )
            }
        }
    }

    @Single
    fun habitatInfoScreenComponentFactory(): HabitatInfoScreenComponent.Factory {
        return object : HabitatInfoScreenComponent.Factory {
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

    @Single
    fun habitatListScreenComponentFactory(
        habitatRepository: HabitatRepository,
        habitatBuildDialogComponentFactory: HabitatBuildDialogComponent.Factory,
        habitatDismantleDialogComponentFactory: HabitatDismantleDialogComponent.Factory,
        messageDialogComponentFactory: com.alaershov.mars_colony.message_dialog.component.MessageDialogComponent.Factory,
    ): HabitatListScreenComponent.Factory {
        return object : HabitatListScreenComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
                onBackClick: () -> Unit,
            ): HabitatListScreenComponent {
                return DefaultHabitatListScreenComponent(
                    componentContext = componentContext,
                    onBackClick = onBackClick,
                    habitatRepository = habitatRepository,
                    habitatBuildDialogComponentFactory = habitatBuildDialogComponentFactory,
                    habitatDismantleDialogComponentFactory = habitatDismantleDialogComponentFactory,
                    messageDialogComponentFactory = messageDialogComponentFactory,
                )
            }
        }
    }
}
