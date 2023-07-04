package com.alaershov.bottomsheet_decompose_sample.main_screen

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class MainScreenBottomSheetConfig : Parcelable {

    object Info : MainScreenBottomSheetConfig()

    data class Confirm(
        val question: String,
    ) : MainScreenBottomSheetConfig()
}
