package com.ruskaof.feature_product_impl.di

import com.ruskaof.feature_product_impl.data.repository_impl.ProductsListRepositoryImpl
import com.ruskaof.feature_product_impl.domain.repository.ProductsListRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindProductsListRepository(repository: ProductsListRepositoryImpl): ProductsListRepository
}