package com.alaershov.mars_colony.message_dialog.di

import com.alaershov.mars_colony.message_dialog.component.DefaultMessageDialogComponent
import com.alaershov.mars_colony.message_dialog.component.MessageDialogComponent
import dagger.Binds
import dagger.Module

@Module
interface MessageDialogComponentDiModule {

    @Binds
    fun messageDialogComponentFactory(
        impl: DefaultMessageDialogComponent.Factory
    ): MessageDialogComponent.Factory
}
