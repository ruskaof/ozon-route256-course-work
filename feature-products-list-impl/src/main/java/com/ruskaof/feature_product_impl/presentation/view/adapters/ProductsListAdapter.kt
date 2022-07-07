package com.ruskaof.feature_product_impl.presentation.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.presentation.view.CustomButton
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class ProductsListAdapter(
    private val onProductClickListener: (guid: String) -> Unit,
) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    var data: List<ProductInListVO> = emptyList()

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
        private val addToCartButton: CustomButton = view.findViewById(R.id.addToCartBTN)


        init {
            view.setOnClickListener { onProductClickListener.invoke(guid) }
            addToCartButton.setOnClickListener { onAddToCartClick() }
        }


        @SuppressLint("CheckResult")
        private fun onAddToCartClick() {
            addToCartButton.state = CustomButton.State.LOADING
            addToCartButton.text = "Loading"
            addToCartButton.setOnClickListener { }
            addingToCartImitation().observeOn(AndroidSchedulers.mainThread()).subscribe { _ ->
                addToCartButton.state = CustomButton.State.DONE
                addToCartButton.text = "В корзине"
            }
        }

        private fun addingToCartImitation(): Single<Boolean> {
            return Single.timer(2, TimeUnit.SECONDS).map { true }
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

        holder.photosPager.adapter = PhotosViewPagerAdapter(images, holder.itemView.context)


        holder.guid = obj.guid

        holder.viewCounter.text = viewCounter.toString()
        holder.name.text = name
        holder.price.text = price
        holder.ratingBar.rating = rating.toFloat()
    }

    override fun getItemCount(): Int {
        return data.size
    }


}