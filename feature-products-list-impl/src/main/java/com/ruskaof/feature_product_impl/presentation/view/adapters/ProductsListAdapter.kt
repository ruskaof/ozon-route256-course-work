package com.ruskaof.feature_product_impl.presentation.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ruskaof.feature_product_impl.R
import com.ruskaof.feature_product_impl.presentation.view_objects.SectionVO

class ProductsListAdapter(
    private val onProductClickListener: (guid: String) -> Unit,
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {
    var data: List<SectionVO> = emptyList()

    class ViewHolder(
        private val view: View,
    ) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
        val titleTW: TextView = view.findViewById(R.id.sectionNameTW)

        val context
            get() = view.context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.product_section, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = data[position]
        val sectionName = obj.title
        val childItems = obj.items

        val childAdapter = ProductsSectionAdapter(childItems, onProductClickListener)

        val layoutManager = GridLayoutManager(holder.context, 2)


        holder.titleTW.text = sectionName
        holder.recyclerView.adapter = childAdapter
        holder.recyclerView.layoutManager = layoutManager

        childAdapter.notifyDataSetChanged()
        Log.d("debuug", "onBindViewHolder: parent binded")
    }

    override fun getItemCount(): Int {
        return data.size
    }


}