package com.ruskaof.core_network_impl.di

import com.ruskaof.core_network_impl.data.BASE_URL
import com.ruskaof.core_network_impl.data.ServiceApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ServiceApiModule {
    @Provides
    fun provideServiceApi() : ServiceApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ServiceApi::class.java)
    }
}