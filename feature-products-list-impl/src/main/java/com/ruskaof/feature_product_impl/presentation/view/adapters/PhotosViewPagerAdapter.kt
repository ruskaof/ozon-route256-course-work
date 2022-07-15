package com.ruskaof.feature_product_impl.presentation.view.adapters

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily

class PhotosViewPagerAdapter(private val imagesLinks: List<String>, private val context: Context) :
    PagerAdapter() {
    override fun getCount(): Int {
        return imagesLinks.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ShapeableImageView(context)
        imageView.shapeAppearanceModel = imageView.shapeAppearanceModel.toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, convertDpToPx(15F, imageView.resources)).build()
        imageView.scaleType = ImageView.ScaleType.CENTER
        Glide.with(context).load(imagesLinks[position]).into(imageView)
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }

    private fun convertDpToPx(dp: Float, resources: Resources): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
    }
}
