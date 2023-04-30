
package com.udacity.project4.utils

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean


class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Registered observers but only one will be notified of changes.")
        }

        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }

    private fun isPending(): Boolean {
        return mPending.get()
    }

    private fun setPending(pending: Boolean) {
        mPending.set(pending)
    }

    private fun notifyObserver(observer: Observer<in T>, data: T) {
        observer.onChanged(data)
    }
}
