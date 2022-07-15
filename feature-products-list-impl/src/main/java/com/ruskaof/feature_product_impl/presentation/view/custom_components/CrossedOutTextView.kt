package com.ruskaof.feature_product_impl.presentation.view.custom_components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue

class CrossedOutTextView(context: Context, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private val myPaint: Paint = Paint()
    private val bounds: Rect = Rect()

    init {
        myPaint.color = Color.RED
        myPaint.strokeWidth = convertDpToPx(1F)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.getClipBounds(bounds)

        canvas?.drawLine(
            0f,
            bounds.height() * 0.6F,
            bounds.width().toFloat(),
            bounds.height() * 0.4F,
            myPaint
        )
    }

    private fun convertDpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            resources.displayMetrics
        )
    }
}