package com.ruskaof.core_navigation_impl.di

import android.content.Context
import com.ruskaof.core_context_injector.ContextInjectorComponent
import com.ruskaof.core_network_impl.di.DaggerCoreNetworkComponent
import com.ruskaof.data_updater_impl.di.DaggerDataUpdaterComponent_DataUpdaterDependenciesComponent
import com.ruskaof.data_updater_impl.di.DataUpdaterComponent
import com.ruskaof.feature_pdp_impl.di.DaggerProductInfoFeatureComponent_ProductInfoFeatureDependenciesComponent
import com.ruskaof.feature_pdp_impl.di.ProductInfoFeatureComponent
import com.ruskaof.feature_product_impl.di.DaggerProductFeatureComponent_ProductsListFeatureDependenciesComponent
import com.ruskaof.feature_product_impl.di.ProductFeatureComponent

object FeatureInjectorProxy {
    fun initFeatureProductsDI() {
        ProductFeatureComponent.initAndGet(
            DaggerProductFeatureComponent_ProductsListFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProductNavigation()
                )
                .dataUpdaterApi(
                    DataUpdaterComponent.initAndGet(
                        DaggerDataUpdaterComponent_DataUpdaterDependenciesComponent.builder()
                            .networkApi(DaggerCoreNetworkComponent.builder().build())
                            .build()
                    ).getDataUpdaterApi()
                )
                .build()
        )
    }

    fun initFeaturePDPDI() {
        ProductInfoFeatureComponent.initAndGet(
            DaggerProductInfoFeatureComponent_ProductInfoFeatureDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .productInfoNavigationApi(
                    DaggerCoreNavigationComponent.builder().build().getProductInfoNavigation()
                )
                .dataUpdaterApi(
                    DataUpdaterComponent.initAndGet(
                        DaggerDataUpdaterComponent_DataUpdaterDependenciesComponent.builder()
                            .networkApi(DaggerCoreNetworkComponent.builder().build())
                            .build()
                    ).getDataUpdaterApi()
                )
                .build()
        )
    }

    fun initDataUpdaterDi() {
        DataUpdaterComponent.initAndGet(
            DaggerDataUpdaterComponent_DataUpdaterDependenciesComponent.builder()
                .networkApi(DaggerCoreNetworkComponent.builder().build())
                .build()
        )
    }

    fun initContextInjectorDi(context: Context) {
        ContextInjectorComponent.initAndGet(context)
    }
}
