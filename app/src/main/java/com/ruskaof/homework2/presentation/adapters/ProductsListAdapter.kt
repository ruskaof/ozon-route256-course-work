package com.ruskaof.homework2.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruskaof.homework2.R
import com.ruskaof.homework2.presentation.viewObject.ProductInListVO

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
        val image: String = data[position].image
        val name: String = data[position].name
        val price: String = data[position].price
        val rating = data[position].rating
        val viewCounter = data[position].viewCounter

        holder.guid = data[position].guid

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