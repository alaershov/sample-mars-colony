package com.alaershov.mars_colony.message_dialog.di

import com.alaershov.mars_colony.message_dialog.MessageDialogState
import com.alaershov.mars_colony.message_dialog.component.DefaultMessageDialogComponent
import com.alaershov.mars_colony.message_dialog.component.MessageDialogComponent
import com.arkivanov.decompose.ComponentContext
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("com.alaershov.mars_colony.message_dialog")
class MessageDialogComponentDiModule {

    @Single
    fun messageDialogComponentFactory(): MessageDialogComponent.Factory {
        return object : MessageDialogComponent.Factory {
            override fun create(
                componentContext: ComponentContext,
                dialogState: MessageDialogState,
                onButtonClick: () -> Unit,
            ): MessageDialogComponent {
                return DefaultMessageDialogComponent(
                    componentContext = componentContext,
                    dialogState = dialogState,
                    onButtonClick = onButtonClick,
                )
            }
        }
    }
}
