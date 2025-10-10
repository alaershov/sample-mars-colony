package com.alaershov.mars_colony.habitat.info_screen

import com.arkivanov.decompose.ComponentContext
import com.yandex.yatagan.Assisted
import com.yandex.yatagan.AssistedFactory
import com.yandex.yatagan.AssistedInject

class DefaultHabitatInfoScreenComponent @AssistedInject internal constructor(
    @Assisted
    componentContext: ComponentContext,
    @Assisted
    habitatId: String
) : HabitatInfoScreenComponent, ComponentContext by componentContext {

    @AssistedFactory
    interface Factory : HabitatInfoScreenComponent.Factory {

        override fun create(
            componentContext: ComponentContext,
            habitatId: String,
        ): DefaultHabitatInfoScreenComponent
    }
}
