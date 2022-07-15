package com.ruskaof.feature_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruskaof.core_utils.viewModelCreator
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.di.ProductFeatureComponent
import com.ruskaof.feature_product_impl.domain.interactor.ProductsListInteractor
import com.ruskaof.feature_product_impl.presentation.view.adapters.ProductsListAdapter
import com.ruskaof.feature_product_impl.presentation.view_models.ProductsListViewModel
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import com.ruskaof.feature_product_impl.presentation.view_objects.SectionVO
import com.ruskaof.feature_products_api.ProductNavigationApi
import javax.inject.Inject

class ProductsListFragment : Fragment(R.layout.fragment_products_list) {

    private val adapter = ProductsListAdapter(this::onProductClick)

    private lateinit var progressBar: ProgressBar
    private lateinit var searchView: SearchView

    private var currentData: List<ProductInListVO> = emptyList()


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

    override fun onResume() {
        super.onResume()

        vm.startUpdatingWithInterval()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = requireView().findViewById(R.id.progressBar)
        searchView = requireView().findViewById(R.id.searchView)

        progressBar.isVisible = true

        val parentRW: RecyclerView = requireView().findViewById(R.id.sectionsRW)
        parentRW.layoutManager = LinearLayoutManager(requireView().context)
        parentRW.adapter = adapter
        parentRW.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )


        vm.updateData()

        vm.productListLD.observe(viewLifecycleOwner) { newList ->
            if (newList != null) {
                currentData = newList
                changeListData(currentData)
                parentRW.isVisible = true
                progressBar.isVisible = false
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query.isNullOrBlank()) {
                    Log.d("debuug", "onQueryTextSubmit: empty query \"$query\"")
                    changeListData(currentData)
                    true
                } else {
                    searchWithSpellCheck(query.lowercase())
                    val filteredList =
                        currentData.filter { it.name.lowercase().contains(query.lowercase()) }
                    changeListData(filteredList)
                    true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    return this.onQueryTextSubmit(newText)
                }
                return false
            }

        })
    }


    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        vm.stopUpdatingWithInterval()
        super.onPause()
    }

    private fun onProductClick(guid: String) {
        productNavigationApi.navigateToProductInfo(this, guid)
    }

    private fun changeListData(newList: List<ProductInListVO>) {
        val cheapProductsList = newList.filter { !it.isExpensive }
        val expensiveProductsList = newList.filter { it.isExpensive }


        adapter.data = listOf(
            SectionVO("Дешёвые товары", cheapProductsList),
            SectionVO("Дорогие товары", expensiveProductsList),
        )
        adapter.notifyDataSetChanged()
    }

    fun searchWithSpellCheck(s: String) {

    }
}


