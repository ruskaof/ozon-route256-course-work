package com.ruskaof.feature_pdp_impl.di

import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractor
import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface InteractorModule {
    @Binds
    fun bindProductsListInteractor(interactor: ProductInfoInteractorImpl): ProductInfoInteractor
}