package com.alaershov.mars_colony.habitat.bottom_sheet

import androidx.compose.runtime.Composable
import com.alaershov.mars_colony.bottom_sheet.BottomSheetContentComponent
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialog
import com.alaershov.mars_colony.habitat.build_dialog.HabitatBuildDialogComponent
import com.alaershov.mars_colony.habitat.dismantle_dialog.HabitatDismantleDialog
import com.alaershov.mars_colony.habitat.dismantle_dialog.component.HabitatDismantleDialogComponent
import com.alaershov.mars_colony.message_dialog.MessageDialog
import com.alaershov.mars_colony.message_dialog.component.MessageDialogComponent

@Composable
fun HabitatBottomSheetContent(component: BottomSheetContentComponent) {
    when (component) {
        is HabitatBuildDialogComponent -> {
            HabitatBuildDialog(component)
        }

        is HabitatDismantleDialogComponent -> {
            HabitatDismantleDialog(component)
        }

        is MessageDialogComponent -> {
            MessageDialog(component)
        }
    }
}
