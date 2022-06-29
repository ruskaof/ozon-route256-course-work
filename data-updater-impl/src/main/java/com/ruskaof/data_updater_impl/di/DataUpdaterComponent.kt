package com.ruskaof.data_updater_impl.di

import com.ruskaof.core_network_api.NetworkApi
import com.ruskaof.data_updater_api.DataUpdaterApi
import com.ruskaof.data_updater_impl.workers.ProductsInfoWorker
import com.ruskaof.data_updater_impl.workers.ProductsListWorker
import dagger.Component

@Component(
    modules = [GsonModule::class, DataUpdaterModule::class],
    dependencies = [DataUpdaterDependencies::class]
)
abstract class DataUpdaterComponent {
    companion object {
        @Volatile
        var dataUpdaterComponent: DataUpdaterComponent? = null
            private set

        fun initAndGet(dataUpdaterDependencies: DataUpdaterDependencies): DataUpdaterComponent {
            if (dataUpdaterComponent == null) {
                synchronized(DataUpdaterComponent::class) {
                    if (dataUpdaterComponent == null) {
                        dataUpdaterComponent = DaggerDataUpdaterComponent.builder()
                            .dataUpdaterDependencies(dataUpdaterDependencies).build()
                    }
                }
            }
            return dataUpdaterComponent!!
        }

        fun get(): DataUpdaterComponent {
            if (dataUpdaterComponent == null) {
                throw RuntimeException("Call init and get first")
            } else {
                return dataUpdaterComponent!!
            }
        }
    }

    abstract fun inject(productsInfoWorker: ProductsInfoWorker)
    abstract fun inject(productsListWorker: ProductsListWorker)


    abstract fun getDataUpdaterApi(): DataUpdaterApi

    @Component(dependencies = [NetworkApi::class])
    interface DataUpdaterDependenciesComponent : DataUpdaterDependencies
}