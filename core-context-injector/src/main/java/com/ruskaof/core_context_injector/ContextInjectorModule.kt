package com.ruskaof.core_context_injector

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextInjectorModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }
}