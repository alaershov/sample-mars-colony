package com.alaershov.mars_colony.message_dialog.di

import com.alaershov.mars_colony.message_dialog.MessageDialogState
import com.alaershov.mars_colony.message_dialog.component.DefaultMessageDialogComponent
import com.alaershov.mars_colony.message_dialog.component.MessageDialogComponent
import org.koin.core.module.Module
import org.koin.dsl.module

val messageDialogKoinModule: Module = module {
    factory<MessageDialogComponent.Factory> {
        object : MessageDialogComponent.Factory {
            override fun create(
                componentContext: com.arkivanov.decompose.ComponentContext,
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
