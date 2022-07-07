package com.ruskaof.feature_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
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
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import com.ruskaof.feature_products_api.ProductNavigationApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import java.util.function.Predicate
import javax.inject.Inject

class ProductsListPageFragment(private val cheapProducts: Boolean = true) :
    Fragment(R.layout.fragment_products_list_page) {
    private val adapter = ProductsListAdapter(this::onProductClick)

    private lateinit var progressBar: ProgressBar


    private val productsFilterPredicate: Predicate<ProductInListVO> =
        Predicate<ProductInListVO> { item ->
            try {
                if (cheapProducts) {
                    item.price.toDouble() < 100
                } else {
                    item.price.toDouble() >= 100
                }
            } catch (e: Exception) {
                false
            }
        }


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

        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireView().context)
        recyclerView.adapter = adapter


        vm.updateData(this)
        vm.productListLD.observe(viewLifecycleOwner) { newList ->
            if (newList != null) {
                adapter.data = newList.filter { productsFilterPredicate.test(it) }
                adapter.notifyDataSetChanged()
                progressBar.isVisible = false
            }
        }

    }

    override fun onPause() {
        disposable.dispose()
        super.onPause()
    }

    private fun onProductClick(guid: String) {
        productNavigationApi.navigateToProductInfo(this, guid)
    }


}


