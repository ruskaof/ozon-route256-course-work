package com.ruskaof.feature_product_impl.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.presentation.view_objects.ProductInListVO

class ProductsListAdapter(
    private val onProductClickListener: (guid: String) -> Unit
) :
    RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    var data: List<ProductInListVO> = emptyList()

    class ViewHolder(
        view: View,
        private val onProductClickListener: (guid: String) -> Unit,
    ) : RecyclerView.ViewHolder(view), View.OnClickListener {
        lateinit var guid: String

        val image: ImageView = view.findViewById(R.id.productIV)
        val name: TextView = view.findViewById(R.id.nameTV)
        val price: TextView = view.findViewById(R.id.priceTV)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingView)
        val viewCounter: TextView = view.findViewById(R.id.viewCounter)


        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onProductClickListener.invoke(guid)
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
        val image: String = obj.image
        val name: String = obj.name
        val price: String = obj.price
        val rating = obj.rating
        val viewCounter = obj.viewCounter

        holder.guid = obj.guid

        holder.viewCounter.text = viewCounter.toString()
        holder.name.text = name
        holder.price.text = price
        holder.ratingBar.rating = rating.toFloat()
        Glide.with(holder.itemView.context).load(image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return data.size
    }

}