package com.alaershov.mars_colony.main_screen.bottom_sheet

import androidx.compose.runtime.Composable
import com.alaershov.mars_colony.bottomsheet.BottomSheetContentComponent
import com.alaershov.mars_colony.confirm_dialog.ConfirmContentComponent
import com.alaershov.mars_colony.confirm_dialog.ConfirmDialog
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialog
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialog
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialogComponent
import com.alaershov.mars_colony.info_dialog.InfoContentComponent
import com.alaershov.mars_colony.info_dialog.InfoDialog

@Composable
fun MainScreenBottomSheetContent(component: BottomSheetContentComponent) {
    when (component) {
        is ConfirmContentComponent -> {
            ConfirmDialog(component)
        }

        is InfoContentComponent -> {
            InfoDialog(component)
        }

        is HabitatBuildDialogComponent -> {
            HabitatBuildDialog(component)
        }

        is HabitatDismantleDialogComponent -> {
            HabitatDismantleDialog(component)
        }
    }
}
