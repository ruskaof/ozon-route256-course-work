package com.ruskaof.core_navigation_impl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ruskaof.core_navigation_impl.di.FeatureInjectorProxy
import com.ruskaof.feature_product_impl.presentation.view.ProductsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        initNonFeatureDI()
        openProductsFragment()
    }


    private fun openProductsFragment() {
        FeatureInjectorProxy.initFeatureProductsDI()
        val newFragment = ProductsListFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainerView,
                newFragment,
                ProductsListFragment::class.simpleName
            )
            .commit()
    }


    private fun initNonFeatureDI() {
        FeatureInjectorProxy.initDataUpdaterDi()
        FeatureInjectorProxy.initContextInjectorDi(applicationContext)
    }

}