package com.alaershov.mars_colony.habitat.bottom_sheet

import kotlinx.serialization.Serializable

@Serializable
sealed class HabitatBottomSheetConfig {

    @Serializable
    data object HabitatBuild : HabitatBottomSheetConfig()

    @Serializable
    data class HabitatDismantle(
        val habitatId: String
    ) : HabitatBottomSheetConfig()

    @Serializable
    data object ConfirmDismantle : HabitatBottomSheetConfig()
}
