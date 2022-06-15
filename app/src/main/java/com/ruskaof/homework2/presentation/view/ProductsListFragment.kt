package com.ruskaof.homework2.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruskaof.homework2.R
import com.ruskaof.homework2.data.di.ServiceLocator
import com.ruskaof.homework2.presentation.adapters.ProductsListAdapter
import com.ruskaof.homework2.presentation.viewModel.ProductsListViewModel
import com.ruskaof.homework2.presentation.viewModel.viewModelCreator

class ProductsListFragment : Fragment(R.layout.fragment_products_list) {
    private val adapter = ProductsListAdapter(this::onProductClick)

    private val vm: ProductsListViewModel by viewModelCreator {
        ProductsListViewModel(ServiceLocator().productsInteractor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = requireView().findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireView().context)
        recyclerView.adapter = adapter

        vm.productListLD.observe(viewLifecycleOwner) {
            adapter.data = it
        }
    }


    private fun onProductClick(guid: String) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, ProductInfoFragment.Builder().setGuid(guid).build())
            addToBackStack(null)
            commit()

            vm.increaseViewCounter(guid)
        }
    }
}