package com.ruskaof.feature_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruskaof.core_utils.viewModelCreator
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.di.ProductFeatureComponent
import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractor
import com.ruskaof.feature_product_impl.presentation.view.adapters.ProductsListAdapter
import com.ruskaof.feature_product_impl.presentation.view_models.ProductsListViewModel
import com.ruskaof.feature_products_api.ProductNavigationApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductsListFragment : Fragment(R.layout.fragment_products_list) {
    private val adapter = ProductsListAdapter(this::onProductClick)

    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var productsInteractor: ProductsListInteractor


    @Inject
    lateinit var productNavigationApi: ProductNavigationApi

    private lateinit var disposable: Disposable

    private val vm: ProductsListViewModel by viewModelCreator {
        ProductsListViewModel(productsInteractor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)

    }

    override fun onResume() {

        disposable = Observable.interval(5, TimeUnit.MINUTES)
            .timeInterval()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                vm.updateData(this)
            }
        super.onResume()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = requireView().findViewById(R.id.progressBar)
        progressBar.isVisible = true

        vm.updateData(this)
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireView().context)
        recyclerView.adapter = adapter

        vm.productListLD.observe(viewLifecycleOwner) {
            if (it != null) {
                Log.d("debugg", "onViewCreated: updated data")

                adapter.data = it
                adapter.notifyDataSetChanged()
                progressBar.isVisible = false
            } else {
                Log.d("debugg", "onViewCreated: null?")
            }
        }

    }


    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }

        disposable.dispose()
        super.onPause()
    }

    private fun onProductClick(guid: String) {
        vm.increaseViewCounter(guid)
        adapter.notifyDataSetChanged()
        productNavigationApi.navigateToProductInfo(this, guid)
    }
}


