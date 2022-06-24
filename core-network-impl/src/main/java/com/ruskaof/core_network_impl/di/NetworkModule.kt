package com.ruskaof.core_network_impl.di

import com.ruskaof.core_network_impl.data.ProductApiImpl
import com.ruskaof.core_network_api.ProductApi
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @Binds
    fun bindProductsListApi(api: ProductApiImpl): ProductApi
}