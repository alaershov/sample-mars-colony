package com.alaershov.mars_colony.power.list_screen

import com.arkivanov.decompose.ComponentContext

interface PowerPlantListScreenComponent {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
        ): PowerPlantListScreenComponent
    }
}
