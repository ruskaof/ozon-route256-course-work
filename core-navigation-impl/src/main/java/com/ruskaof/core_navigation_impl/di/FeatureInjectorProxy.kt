package com.ruskaof.core_navigation_impl.di

import com.ruskaof.core_network_impl.di.DaggerCoreNetworkComponent
import com.ruskaof.feature_pdp_impl.di.DaggerProductInfoFeatureComponent_ProductInfoFeatureDependenciesComponent
import com.ruskaof.feature_pdp_impl.di.ProductInfoFeatureComponent
import com.ruskaof.feature_product_impl.di.DaggerProductFeatureComponent
import com.ruskaof.feature_product_impl.di.DaggerProductFeatureComponent_ProductsListFeatureDependenciesComponent
import com.ruskaof.feature_product_impl.di.ProductFeatureComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI() {
        ProductFeatureComponent.initAndGet(
            DaggerProductFeatureComponent_ProductsListFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productNavigationApi(DaggerCoreNavigationComponent.builder().build().getProductNavigation())
                .build()
        )
    }

    fun initFeaturePDPDI() {
        ProductInfoFeatureComponent.initAndGet(
            DaggerProductInfoFeatureComponent_ProductInfoFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productInfoNavigationApi(DaggerCoreNavigationComponent.builder().build().getProductInfoNavigation())
                .build()
        )
    }

}
