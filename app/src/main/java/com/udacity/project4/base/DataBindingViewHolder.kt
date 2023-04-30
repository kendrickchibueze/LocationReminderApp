package com.udacity.project4.base

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView



class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        setVariable(item)
        executePendingBindings()
    }

    private fun setVariable(item: T) {
        binding.setVariable(BR.item, item)
    }

    private fun executePendingBindings() {
        binding.executePendingBindings()
    }
}
