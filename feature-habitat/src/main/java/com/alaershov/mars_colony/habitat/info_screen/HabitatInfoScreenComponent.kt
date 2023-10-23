package com.alaershov.mars_colony.habitat.info_screen

import com.arkivanov.decompose.ComponentContext

interface HabitatInfoScreenComponent {

    interface Factory {

        fun create(
            componentContext: ComponentContext,
            habitatId: String,
        ): HabitatInfoScreenComponent
    }
}
