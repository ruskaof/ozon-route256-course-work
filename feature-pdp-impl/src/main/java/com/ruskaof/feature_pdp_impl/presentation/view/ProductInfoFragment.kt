package com.ruskaof.feature_pdp_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ruskaof.feature_pdp_api.ProductInfoNavigationApi
import com.ruskaof.feature_pdp_impl.R
import com.ruskaof.feature_pdp_impl.di.ProductInfoFeatureComponent
import com.ruskaof.feature_pdp_impl.domain.interactor.ProductInfoInteractor
import com.ruskaof.feature_pdp_impl.presentation.view_models.ProductInfoViewModel
import com.ruskaof.feature_products_api.ProductNavigationApi
import ru.ozon.route256.core_utils.viewModelCreator
import javax.inject.Inject


class ProductInfoFragment : Fragment(R.layout.fragment_product_info) {
    @Inject
    lateinit var productInfoInteractor: ProductInfoInteractor

    @Inject
    lateinit var productInfoNavigationApi: ProductInfoNavigationApi

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
