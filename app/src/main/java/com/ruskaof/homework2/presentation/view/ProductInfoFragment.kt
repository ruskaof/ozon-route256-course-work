package com.ruskaof.homework2.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ruskaof.homework2.R
import com.ruskaof.homework2.data.di.ServiceLocator
import com.ruskaof.homework2.presentation.viewModel.ProductInfoViewModel
import com.ruskaof.homework2.presentation.viewModel.viewModelCreator

class ProductInfoFragment : Fragment(R.layout.fragment_product_info) {

    private val vm: ProductInfoViewModel by viewModelCreator {
        ProductInfoViewModel(
            ServiceLocator().productsInteractor,
            requireArguments().getString("guid")!!
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val price: TextView = requireView().findViewById(R.id.priceTV)
        val name: TextView = requireView().findViewById(R.id.nameTV)
        val rating: RatingBar = requireView().findViewById(R.id.ratingView)
        val image: ImageView = requireView().findViewById(R.id.productIV)
        val info: TextView = requireView().findViewById(R.id.mainInfoTW)


        vm.productInfoLD.observe(viewLifecycleOwner) {
            price.text = it.price
            name.text = it.name
            rating.rating = it.rating.toFloat()
            info.text = it.description
            Glide.with(requireContext().applicationContext).load(it.images[0]).into(image)
        }
    }

}