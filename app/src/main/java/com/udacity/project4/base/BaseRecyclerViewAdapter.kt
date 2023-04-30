package com.udacity.project4.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T>(private val callback: ((item: T) -> Unit)? = null) :
    RecyclerView.Adapter<DataBindingViewHolder<T>>() {



    private var _items = mutableListOf<T>()
    val items: List<T> get() = _items
    override fun getItemCount() = _items.size



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder<T> {
        val binding = createBinding(parent, viewType)
        setBindingLifecycleOwner(binding)
        return DataBindingViewHolder(binding)
    }

    private fun createBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DataBindingUtil.inflate(layoutInflater, getLayoutRes(viewType), parent, false)
    }

    private fun setBindingLifecycleOwner(binding: ViewDataBinding) {
        binding.lifecycleOwner = getLifecycleOwner()
    }


   override fun onBindViewHolder(holder: DataBindingViewHolder<T>, position: Int) {
       val item = getItem(position)
       holder.bind(item)
       holder.itemView.setOnClickListener { onClickListener(item) }
   }

    private val onClickListener: (T) -> Unit = { item ->
        callback?.invoke(item)
    }


    fun getItem(position: Int) = _items[position]

    fun addData(items: List<T>) {
        _items.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() {
        _items.clear()
        notifyDataSetChanged()
    }

    @LayoutRes
    abstract fun getLayoutRes(viewType: Int): Int

    open fun getLifecycleOwner(): LifecycleOwner? = null

}

