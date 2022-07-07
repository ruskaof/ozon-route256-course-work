package com.ruskaof.feature_product_impl.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.di.ProductFeatureComponent
import com.ruskaof.feature_products_api.ProductNavigationApi
import javax.inject.Inject

class ProductsListFragment : Fragment(R.layout.fragment_products_list) {
    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    @Inject
    lateinit var productNavigationApi: ProductNavigationApi


    override fun onAttach(context: Context) {
        super.onAttach(context)
        ProductFeatureComponent.productFeatureComponent?.inject(this)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = requireView().findViewById(R.id.pager)
        viewPager.adapter = ScreenSlidePagerAdapter(requireActivity().supportFragmentManager)

        tabLayout = requireView().findViewById(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }


    override fun onPause() {
        if (isRemoving) {
            if (productNavigationApi.isFeatureClosed(this)) {
                ProductFeatureComponent.resetComponent()
            }
        }
        super.onPause()
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = 2

        override fun getItem(position: Int): Fragment = ProductsListPageFragment(position == 0)

        override fun getPageTitle(position: Int): CharSequence {
            return if (position == 0) {
                "Дешёвые товары"
            } else {
                "Дорогие товары"
            }
        }
    }
}


