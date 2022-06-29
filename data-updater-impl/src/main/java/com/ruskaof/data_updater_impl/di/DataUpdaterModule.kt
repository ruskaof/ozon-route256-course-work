package com.ruskaof.data_updater_impl.di

import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_impl.DataUpdaterImpl
import dagger.Module
import dagger.Provides

@Module
class DataUpdaterModule {
    @Provides
    fun provideDataUpdater(): DataUpdaterApi {
        return DataUpdaterImpl()
    }
}