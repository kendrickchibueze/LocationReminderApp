package com.udacity.project4.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.udacity.project4.base.BaseRecyclerViewAdapter


fun <T> RecyclerView.setup(adapter: BaseRecyclerViewAdapter<T>) {
    layoutManager = LinearLayoutManager(context)
    this.adapter = adapter
}

fun Fragment.setTitle(title: String) {
    (activity as? AppCompatActivity)?.supportActionBar?.title = title
}

fun Fragment.setDisplayHomeAsUpEnabled(bool: Boolean) {
    (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(bool)
}

fun View.fadeIn() {
    if (visibility != View.VISIBLE) {
        alpha = 0f
        visibility = View.VISIBLE
        animate().alpha(1f).setListener(null)
    }
}

fun View.fadeOut() {
    if (visibility == View.VISIBLE) {
        animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                alpha = 1f
                visibility = View.GONE
            }
        })
    }
}
