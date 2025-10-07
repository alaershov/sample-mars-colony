package com.alaershov.mars_colony.power.list_screen.component

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultPowerPlantListScreenComponent @AssistedInject constructor(
    @Assisted
    componentContext: ComponentContext,
) : PowerPlantListScreenComponent, ComponentContext by componentContext {

    @AssistedFactory
    interface Factory : PowerPlantListScreenComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
        ): DefaultPowerPlantListScreenComponent
    }
}
