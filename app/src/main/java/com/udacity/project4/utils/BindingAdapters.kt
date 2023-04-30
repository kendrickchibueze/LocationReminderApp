package com.udacity.project4.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.udacity.project4.base.BaseRecyclerViewAdapter


object BindingAdapters {

    @Suppress("UNCHECKED_CAST")
    @BindingAdapter("android:liveData")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: LiveData<List<T>>?) {
        items?.value?.let { itemList ->
            val adapter = recyclerView.adapter as? BaseRecyclerViewAdapter<T>
            adapter?.clear()
            adapter?.addData(itemList)
        }
    }


    @BindingAdapter("android:fadeVisible")
    @JvmStatic
    fun setFadeVisible(view: View, visible: Boolean? = true) {
        if (view.tag == null) {
            view.tag = true
            view.visibility = if (visible == true) View.VISIBLE else View.GONE
        } else {
            animateVisibility(view, visible == true)
        }
    }

    private fun animateVisibility(view: View, isVisible: Boolean) {
        view.animate().cancel()
        if (isVisible) {
            if (view.visibility == View.GONE)
                view.fadeIn()
        } else {
            if (view.visibility == View.VISIBLE)
                view.fadeOut()
        }
    }

    private fun View.fadeIn() {
        alpha = 0f
        visibility = View.VISIBLE
        animate().alpha(1f).setDuration(200L).setListener(null)
    }

    private fun View.fadeOut() {
        animate().alpha(0f).setDuration(200L).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = View.GONE
            }
        })
    }

}