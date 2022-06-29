package com.ruskaof.feature_product_impl.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class GsonModule {
    @Provides
    fun provideGson(): Gson = Gson()
}