package com.ruskaof.feature_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.di.ProductFeatureComponent
import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractor
import com.ruskaof.feature_product_impl.presentation.view.adapters.ProductsListAdapter
import com.ruskaof.feature_product_impl.presentation.view_models.ProductsListViewModel
import com.ruskaof.feature_products_api.ProductNavigationApi
import ru.ozon.route256.core_utils.viewModelCreator
import javax.inject.Inject


class ProductsListFragment : Fragment(R.layout.fragment_products_list) {
    private val adapter = ProductsListAdapter(this::onProductClick)

    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var productsInteractor: ProductsListInteractor

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi


    private val vm: ProductsListViewModel by viewModelCreator {
        ProductsListViewModel(productsInteractor)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = requireView().findViewById(R.id.progressBar)
        progressBar.isVisible = true

        vm.updateData(requireContext(), this)
        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireView().context)
        recyclerView.adapter = adapter

        vm.productListLD.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.data = it
                adapter.notifyDataSetChanged()
                progressBar.isVisible = false
            }
        }

    }


    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private fun onProductClick(guid: String) {
        vm.increaseViewCounter(guid)
        adapter.notifyDataSetChanged()
        productNavigationApi.navigateToProductInfo(this, guid)
    }
}