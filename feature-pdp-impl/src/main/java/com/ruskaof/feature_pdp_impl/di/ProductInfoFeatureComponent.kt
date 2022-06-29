package com.ruskaof.feature_pdp_impl.di

import com.ruskaof.core_network_api.NetworkApi
import com.ruskaof.core_utils.di.PerFeature
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi
import com.ruskaof.feature_pdp_impl.presentation.view.ProductInfoFragment
import dagger.Component

@Component(
    modules = [InteractorModule::class, RepositoryModule::class, GsonModule::class],
    dependencies = [ProductInfoFeatureDependencies::class]
)
@PerFeature
abstract class ProductInfoFeatureComponent {
    companion object {
        @Volatile
        var productInfoFeatureComponent: ProductInfoFeatureComponent? = null
            private set

        fun initAndGet(productListFeatureDependencies: ProductInfoFeatureDependencies): ProductInfoFeatureComponent? {
            if (productInfoFeatureComponent == null) {
                synchronized(ProductInfoFeatureComponent::class) {
                    if (productInfoFeatureComponent == null) {
                        productInfoFeatureComponent = DaggerProductInfoFeatureComponent.builder()
                            .productInfoFeatureDependencies(productListFeatureDependencies)
                            .build()
                    }
                }
            }
            return productInfoFeatureComponent
        }

        fun get(): ProductInfoFeatureComponent {
            if (productInfoFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productListFeatureDependencies: ProductInfoFeatureDependencies)' method")
            }
            return productInfoFeatureComponent!!
        }

        fun resetComponent() {
            productInfoFeatureComponent = null
        }

    }

    abstract fun inject(fragment: ProductInfoFragment)


    @Component(dependencies = [NetworkApi::class, ProductInfoNavigationApi::class, DataUpdaterApi::class])
    @PerFeature
    interface ProductInfoFeatureDependenciesComponent : ProductInfoFeatureDependencies
}