package com.ruskaof.homework2.domain.interactors

import com.ruskaof.homework2.domain.repositories.ProductsRepository
import com.ruskaof.homework2.presentation.mappers.toVO
import com.ruskaof.homework2.presentation.viewObject.ProductInListVO
import com.ruskaof.homework2.presentation.viewObject.ProductVO

class ProductsInteractorImpl(private val productsRepository: ProductsRepository) :
    ProductsInteractor {
    override fun getProducts(): List<ProductInListVO> {
        return productsRepository.getProducts().map { product -> product.toVO() }
    }

    override fun getProductById(guid: String): ProductVO {
        return productsRepository.getProductById(guid).toVO()
    }

    override fun increaseViewCounter(guid: String) {
        productsRepository.increaseViewCounter(guid)
    }
}