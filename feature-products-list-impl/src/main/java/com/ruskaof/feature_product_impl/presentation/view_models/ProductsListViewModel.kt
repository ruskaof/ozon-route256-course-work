package com.ruskaof.feature_product_impl.presentation.view_models


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractor
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes
import kotlin.time.ExperimentalTime


class ProductsListViewModel(private val productsInteractor: ProductsListInteractor) : ViewModel() {
    private val _productsListLD: MutableLiveData<List<ProductInListVO>> = MutableLiveData()
    val productListLD: LiveData<List<ProductInListVO>> = _productsListLD

    private lateinit var updatingJob: Job

    @Volatile
    private var isUpdatingNow = false

    init {
        productsInteractor.getProductsList().onEach {
            _productsListLD.value = it
        }.launchIn(viewModelScope)
    }

    fun updateData() {
        productsInteractor.updateData()
    }


    fun increaseViewCounter(guid: String) {
        productsInteractor.increaseViewCounter(guid)
    }

    @OptIn(ExperimentalTime::class)
    fun startUpdatingWithInterval() {
        val duration: Duration = 5.minutes
        updatingJob = viewModelScope.launch {
            isUpdatingNow = true
            while (isUpdatingNow) {
                delay(duration)
                updateData()
            }
        }
    }

    fun stopUpdatingWithInterval() {
        isUpdatingNow = false
        updatingJob.cancel()
    }

}