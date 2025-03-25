package com.alaershov.mars_colony.root.bottom_sheet

import kotlinx.serialization.Serializable

@Serializable
sealed class RootBottomSheetConfig {

    @Serializable
    data object HabitatBuild : RootBottomSheetConfig()

    @Serializable
    data class HabitatDismantle(
        val habitatId: String
    ) : RootBottomSheetConfig()
}
