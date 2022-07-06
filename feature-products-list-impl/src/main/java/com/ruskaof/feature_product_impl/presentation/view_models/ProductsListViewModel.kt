package com.ruskaof.feature_product_impl.presentation.view_models


import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractor
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO


class ProductsListViewModel(private val productsInteractor: ProductsListInteractor) : ViewModel() {
    private val _productsListLD: MutableLiveData<List<ProductInListVO>> = MutableLiveData()
    val productListLD: LiveData<List<ProductInListVO>> = _productsListLD


    fun updateData(context: Context, lifecycleOwner: LifecycleOwner) {
//        productsInteractor.updateData(context, lifecycleOwner).observe(lifecycleOwner) {
//            _productsListLD.value = productsInteractor.getProductsList(context)
//        }
    }


    fun increaseViewCounter(guid: String) {
//        productsInteractor.increaseViewCounter(guid)
//        productsList.first() { it.guid == guid }.viewCounter++
//        _productsListLD.value = productsList
    }

}