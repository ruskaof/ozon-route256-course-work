package com.ruskaof.core_network_impl.di

import com.ruskaof.core_network_impl.data.ProductApiImpl
import com.ruskaof.core_network_api.ProductApi
import com.ruskaof.core_network_impl.data.BASE_URL
import com.ruskaof.core_network_impl.data.ServiceApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface NetworkModule {
    @Binds
    fun bindProductsListApi(api: ProductApiImpl): ProductApi
}