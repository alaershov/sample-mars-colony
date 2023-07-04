package com.alaershov.bottomsheet_decompose_sample.main_screen

import androidx.compose.runtime.Composable
import com.alaershov.bottomsheet_decompose_sample.bottomsheet.BottomSheetContentComponent
import com.alaershov.bottomsheet_decompose_sample.confirm_dialog.ConfirmContentComponent
import com.alaershov.bottomsheet_decompose_sample.confirm_dialog.ConfirmDialog
import com.alaershov.bottomsheet_decompose_sample.info_dialog.InfoContentComponent
import com.alaershov.bottomsheet_decompose_sample.info_dialog.InfoDialog

@Composable
fun MainScreenBottomSheetContent(component: BottomSheetContentComponent) {
    when (component) {
        is ConfirmContentComponent -> {
            ConfirmDialog(component)
        }

        is InfoContentComponent -> {
            InfoDialog(component)
        }
    }
}
