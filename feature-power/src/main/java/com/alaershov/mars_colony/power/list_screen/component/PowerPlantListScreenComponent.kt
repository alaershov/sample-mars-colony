package com.alaershov.mars_colony.power.list_screen.component

import com.arkivanov.decompose.ComponentContext

interface PowerPlantListScreenComponent {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
        ): PowerPlantListScreenComponent
    }
}
