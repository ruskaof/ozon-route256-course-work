package com.ruskaof.feature_pdp_impl.di

import com.ruskaof.feature_pdp_impl.data.repository_impl.ProductInfoRepositoryImpl
import com.ruskaof.feature_pdp_impl.domain.repository.ProductInfoRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindProductInfoRepository(repository: ProductInfoRepositoryImpl): ProductInfoRepository
}