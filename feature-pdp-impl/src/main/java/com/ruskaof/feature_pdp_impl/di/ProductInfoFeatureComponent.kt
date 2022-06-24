package com.ruskaof.feature_pdp_impl.di

import com.ruskaof.core_network_api.NetworkApi
import com.ruskaof.core_utils.di.PerFeature
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi
import com.ruskaof.feature_pdp_impl.data.repository_impl.ProductsInfoWorker
import com.ruskaof.feature_pdp_impl.data.repository_impl.ProductsListWorker
import com.ruskaof.feature_pdp_impl.presentation.view.ProductInfoFragment
import com.ruskaof.feature_products_api.ProductNavigationApi
import dagger.Component

@Component(
    modules = [InteractorModule::class, RepositoryModule::class],
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
    abstract fun inject(productsListWorker: ProductsListWorker)
    abstract fun inject(productsInfoWorker: ProductsInfoWorker)

    @Component(dependencies = [NetworkApi::class, ProductInfoNavigationApi::class])
    @PerFeature
    interface ProductInfoFeatureDependenciesComponent : ProductInfoFeatureDependencies
}