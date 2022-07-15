package com.ruskaof.feature_product_impl.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.presentation.view.custom_components.CustomButton
import com.ruskaof.feature_product_impl.presentation.view.custom_components.ShippingInfoTextView
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO

class ProductsSectionAdapter(
    private val data: List<ProductInListVO> = emptyList(),
    private val onProductClickListener: (guid: String) -> Unit,
) : RecyclerView.Adapter<ProductsSectionAdapter.ViewHolder>() {


    class ViewHolder(
        view: View,
        private val onProductClickListener: (guid: String) -> Unit,
    ) : RecyclerView.ViewHolder(view) {
        lateinit var guid: String

        val name: TextView = view.findViewById(R.id.nameTV)
        val price: TextView = view.findViewById(R.id.priceTV)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingView)
        val viewCounter: TextView = view.findViewById(R.id.viewCounter)
        val photosPager: ViewPager = view.findViewById(R.id.photosPager)
        val tabLayout: TabLayout = view.findViewById(R.id.tabLayout)
        val priceBeforeDiscount: TextView = view.findViewById(R.id.priceBeforeDiscountTW)
        private val addToCartButton: CustomButton = view.findViewById(R.id.addToCartBTN)
        val shippingInfoTW: ShippingInfoTextView = view.findViewById(R.id.shippingInfoTW)
        val discountTW: TextView = view.findViewById(R.id.discountTW)
        val reviewCountTW: TextView = view.findViewById(R.id.reviewCountTW)


        init {
            view.setOnClickListener {
                onProductClickListener.invoke(guid)
            }
            addToCartButton.setOnClickListener {
                onAddToCartClick()
            }
        }

        private fun onAddToCartClick() {
            addToCartButton.state = CustomButton.State.IN_CART
            addToCartButton.setOnClickListener {
                // navigate to cart
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.product_list_item, parent, false)

        return ViewHolder(view, onProductClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = data[position]
        val images = obj.image
        val name: String = obj.name
        val price: String = obj.price
        val rating = obj.rating
        val viewCounter = obj.viewCounter
        val priceBeforeDiscount = obj.priceBeforeDiscount

        val shippingDate = obj.shippingDate
        val shippingCompany = obj.shippingCompanyName
        val sellerCompany = obj.sellerName
        val reviewCount = obj.reviewCount

        holder.photosPager.adapter = PhotosViewPagerAdapter(images, holder.itemView.context)
        holder.reviewCountTW.text = reviewCount
        holder.shippingInfoTW.setShippingInfoText(shippingDate, shippingCompany, sellerCompany)
        holder.guid = obj.guid
        holder.tabLayout.setupWithViewPager(holder.photosPager, true)
        holder.viewCounter.text = viewCounter.toString()
        holder.name.text = name
        holder.price.text = price
        holder.ratingBar.rating = rating.toFloat()
        if (obj.thereWasDiscount) {
            holder.discountTW.text = obj.discount
            holder.priceBeforeDiscount.text = priceBeforeDiscount
            holder.priceBeforeDiscount.isVisible = true
            holder.discountTW.isVisible = true
        } else {
            holder.priceBeforeDiscount.isVisible = false
            holder.discountTW.isVisible = false
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


}