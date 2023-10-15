package com.alaershov.mars_colony.root.bottom_sheet

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

sealed class RootBottomSheetConfig : Parcelable {

    @Parcelize
    data object HabitatBuild : RootBottomSheetConfig()

    @Parcelize
    data class HabitatDismantle(
        val habitatId: String
    ) : RootBottomSheetConfig()
}
