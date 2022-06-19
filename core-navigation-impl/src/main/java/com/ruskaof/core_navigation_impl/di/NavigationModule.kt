package com.ruskaof.core_navigation_impl.di

import com.ruskaof.core_navigation_impl.navigation.ProductInfoNavigationImpl
import com.ruskaof.core_navigation_impl.navigation.ProductNavigationImpl
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi
import com.ruskaof.feature_products_api.ProductNavigationApi
import dagger.Binds
import dagger.Module

@Module
interface NavigationModule {
    @Binds
    fun bindProductNavigation(navigation: ProductNavigationImpl): ProductNavigationApi

    @Binds
    fun bindProductInfoNavigation(navigation: ProductInfoNavigationImpl): ProductInfoNavigationApi
}