package com.ruskaof.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ruskaof.core_utils.viewModelCreator
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi
import com.ruskaof.feature_pdp_impl.R
import com.ruskaof.feature_pdp_impl.di.ProductInfoFeatureComponent
import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractor
import com.ruskaof.feature_pdp_impl.presentation.view_models.ProductInfoViewModel
import javax.inject.Inject


class ProductInfoFragment : Fragment(R.layout.fragment_product_info) {
    @Inject
    lateinit var productInfoInteractor: ProductInfoInteractor

    @Inject
    lateinit var productInfoNavigationApi: ProductInfoNavigationApi

    private lateinit var progressBar: ProgressBar
    private lateinit var itemsCL: ConstraintLayout

    private val vm: ProductInfoViewModel by viewModelCreator {
        ProductInfoViewModel(
            productInfoInteractor,
            requireArguments().getString("guid")!!
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductInfoFeatureComponent.get().inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = requireView().findViewById(R.id.progressBar)
        progressBar.isVisible = true
        itemsCL = requireView().findViewById(R.id.itemsCL)
        itemsCL.isVisible = false


        val price: TextView = requireView().findViewById(R.id.priceTV)
        val name: TextView = requireView().findViewById(R.id.nameTV)
        val rating: RatingBar = requireView().findViewById(R.id.ratingView)
        val image: ImageView = requireView().findViewById(R.id.productIV)
        val info: TextView = requireView().findViewById(R.id.mainInfoTW)


        vm.productInfoLD.observe(viewLifecycleOwner) {
            if (it != null) {
                price.text = it.price
                name.text = it.name
                rating.rating = it.rating.toFloat()
                info.text = it.description
                Glide.with(requireContext().applicationContext).load(it.images[0]).into(image)


                progressBar.isVisible = false
                itemsCL.isVisible = true
            }
        }

        vm.updateData()
    }


    override fun onPause() {
        if (isRemoving) {
            if (productInfoNavigationApi.isFeatureClosed(this)) {
                ProductInfoFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    class Builder {

        private val bundle = Bundle()

        fun setGuid(guid: String): Builder {
            bundle.putString("guid", guid)
            return this
        }
        fun build(): ProductInfoFragment {
            val fragment = ProductInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
