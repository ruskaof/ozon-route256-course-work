package com.ruskaof.homework2.data.di

import com.ruskaof.homework2.data.repositoriesImpl.MockProductsRepositoryImpl
import com.ruskaof.homework2.domain.interactors.ProductsInteractor
import com.ruskaof.homework2.domain.interactors.ProductsInteractorImpl

class ServiceLocator {
    val productsInteractor: ProductsInteractor by lazy {
        ProductsInteractorImpl(
            MockProductsRepositoryImpl()
        )
    }
}
