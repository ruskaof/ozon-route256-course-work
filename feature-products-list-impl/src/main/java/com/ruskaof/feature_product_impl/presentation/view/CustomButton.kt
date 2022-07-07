package com.ruskaof.feature_product_impl.presentation.view

import android.content.Context
import android.util.AttributeSet
import com.ruskaof.feature_product_impl.R

class CustomButton(context: Context, attributeSet: AttributeSet) :
    androidx.appcompat.widget.AppCompatButton(context, attributeSet) {
    companion object {
        private val STATE_NORMAL = intArrayOf(R.attr.state_normal)
        private val STATE_LOADING = intArrayOf(R.attr.state_loading)
        private val STATE_DONE = intArrayOf(R.attr.state_done)
    }

    var state: State = State.NORMAL
        set(value) {
            field = value
            refreshDrawableState()
        }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState = super.onCreateDrawableState(extraSpace + 3)
        if (state == State.NORMAL) {
            mergeDrawableStates(drawableState, STATE_NORMAL)
        } else if (state == State.LOADING) {
            mergeDrawableStates(drawableState, STATE_LOADING)
        } else {
            mergeDrawableStates(drawableState, STATE_DONE)
        }

        // По каким-то причинам приложение крашится, если написать так
//        when (state) {
//            State.NORMAL -> mergeDrawableStates(drawableState, STATE_NORMAL)
//            State.LOADING -> mergeDrawableStates(drawableState, STATE_LOADING)
//            State.DONE -> mergeDrawableStates(drawableState, STATE_DONE)
//        }
        return drawableState
    }

    enum class State {
        NORMAL, LOADING, DONE
    }
}