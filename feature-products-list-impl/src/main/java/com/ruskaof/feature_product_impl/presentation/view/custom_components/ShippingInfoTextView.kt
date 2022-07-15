package com.ruskaof.feature_product_impl.presentation.view.custom_components

import android.content.Context
import android.text.SpannableStringBuilder
import android.util.AttributeSet
import androidx.core.text.color

class ShippingInfoTextView(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    fun setShippingInfoText(shippingDate: String, shippingCompany: String, sellerCompany: String) {
        val spannableString =
            SpannableStringBuilder()
                .color(resources.getColor(com.ruskaof.core_utils.R.color.black)) {
                    append("$shippingDate")
                }
                .color(resources.getColor(com.ruskaof.core_utils.R.color.gray)) {
                    append(" доставит ")
                }
                .color(resources.getColor(com.ruskaof.core_utils.R.color.blue)) {
                    append(shippingCompany)
                }
                .color(resources.getColor(com.ruskaof.core_utils.R.color.gray)) {
                    append(", продавец $sellerCompany")
                }

        this.text = spannableString
    }
}