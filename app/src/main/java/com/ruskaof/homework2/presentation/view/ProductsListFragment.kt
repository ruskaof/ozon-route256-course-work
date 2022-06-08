package com.ruskaof.homework2.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruskaof.homework2.R
import com.ruskaof.homework2.data.di.ServiceLocator
import com.ruskaof.homework2.presentation.adapters.ProductListAdapter
import com.ruskaof.homework2.presentation.viewModel.ProductsListViewModel
import com.ruskaof.homework2.presentation.viewObject.ProductInListVO
import com.ruskaof.homework2.presentation.viewModel.viewModelCreator

class ProductsListFragment : Fragment(R.layout.fragment_products_list),
    ProductListAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductListAdapter
    private lateinit var data: List<ProductInListVO>

    private val vm: ProductsListViewModel by viewModelCreator {
        ProductsListViewModel(ServiceLocator().productsInteractor)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = requireView().findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(requireView().context)

        vm.productListLD.observe(viewLifecycleOwner) {
            data = it
            adapter = ProductListAdapter(it, this)
            recyclerView.adapter = adapter
        }
    }


    override fun onClick(position: Int) {
        val guid = data[position].guid
        val args = Bundle()
        args.putString("guid", guid)

        parentFragmentManager.beginTransaction().apply {
            val productInfoFragment = ProductInfoFragment()
            productInfoFragment.arguments = args
            replace(R.id.fragmentContainerView, productInfoFragment)
            addToBackStack(null)

            vm.increaseViewCounter(guid)
            commit()
        }
    }
}