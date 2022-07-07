package com.ruskaof.feature_product_impl.di

import com.ruskaof.core_network_api.NetworkApi
import com.ruskaof.core_utils.di.PerFeature
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.feature_product_impl.presentation.view.ProductsListFragment
import com.ruskaof.feature_product_impl.presentation.view.ProductsListPageFragment
import com.ruskaof.feature_products_api.ProductNavigationApi
import dagger.Component

@Component(
    modules = [InteractorModule::class, RepositoryModule::class, GsonModule::class],
    dependencies = [ProductsListFeatureDependencies::class]
)
@PerFeature
abstract class ProductFeatureComponent {
    companion object {
        @Volatile
        var productFeatureComponent: ProductFeatureComponent? = null
            private set

        fun initAndGet(productListFeatureDependencies: ProductsListFeatureDependencies): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                synchronized(ProductFeatureComponent::class) {
                    if (productFeatureComponent == null) {
                        productFeatureComponent = DaggerProductFeatureComponent.builder()
                            .productsListFeatureDependencies(productListFeatureDependencies)
                            .build()
                    }
                }
            }
            return productFeatureComponent
        }

        fun get(): ProductFeatureComponent? {
            if (productFeatureComponent == null) {
                throw RuntimeException("You must call 'initAndGet(productFeatureDependencies: ProductFeatureDependencies)' method")
            }
            return productFeatureComponent
        }

        fun resetComponent() {
            productFeatureComponent = null
        }

    }

    abstract fun inject(fragment: ProductsListFragment)
    abstract fun inject(fragment: ProductsListPageFragment)


    @Component(dependencies = [NetworkApi::class, ProductNavigationApi::class, DataUpdaterApi::class])
    @PerFeature
    interface ProductsListFeatureDependenciesComponent : ProductsListFeatureDependencies
}