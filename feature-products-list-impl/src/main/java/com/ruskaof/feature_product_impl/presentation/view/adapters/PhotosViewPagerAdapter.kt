package com.ruskaof.feature_product_impl.presentation.view.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide

class PhotosViewPagerAdapter(private val imagesLinks: List<String>, private val context: Context) :
    PagerAdapter() {
    override fun getCount(): Int {
        return imagesLinks.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER
        Glide.with(context).load(imagesLinks[position]).into(imageView)
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }
}

//        Glide.with(holder.itemView.context).load(image).into(holder.image)