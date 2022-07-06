package com.ruskaof.core_context_injector

import android.content.Context
import com.ruskaof.core_context_needer_api.ContextNeeder
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ContextInjectorModule::class])
abstract class ContextInjectorComponent {
    companion object {
        @Volatile
        private var contextInjectorComponent: ContextInjectorComponent? = null

        fun initAndGet(context: Context) {
            if (contextInjectorComponent == null) {
                synchronized(ContextInjectorComponent::class) {
                    if (contextInjectorComponent == null) {
                        contextInjectorComponent = DaggerContextInjectorComponent.builder()
                            .contextInjectorModule(ContextInjectorModule(context))
                            .build()
                    }
                }
            }
        }

        fun get(): ContextInjectorComponent {
            if (contextInjectorComponent != null) {
                return contextInjectorComponent!!
            } else {
                throw RuntimeException("call init and get first")
            }
        }
    }

    abstract fun inject(contextNeeder: ContextNeeder)
}