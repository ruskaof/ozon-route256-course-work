package com.ruskaof.feature_product_impl.di

import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractor
import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface InteractorModule {
    @Binds
    fun bindProductsListInteractor(interactor: ProductsListInteractorImpl): ProductsListInteractor
}