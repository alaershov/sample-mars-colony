package com.alaershov.mars_colony.power.list_screen.component

import com.arkivanov.decompose.ComponentContext
import com.yandex.yatagan.Assisted
import com.yandex.yatagan.AssistedFactory
import com.yandex.yatagan.AssistedInject

class DefaultPowerPlantListScreenComponent @AssistedInject internal constructor(
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
