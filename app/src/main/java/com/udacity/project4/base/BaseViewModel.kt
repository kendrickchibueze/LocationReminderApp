package com.udacity.project4.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.udacity.project4.utils.SingleLiveEvent


abstract class BaseViewModel(app: Application) : AndroidViewModel(app) {

    val navigationCommand = SingleLiveEvent<NavigationCommand>()
    val showErrorMessage = SingleLiveEvent<String>()
    val showSnackBar = SingleLiveEvent<String>()
    val showSnackBarInt = SingleLiveEvent<Int>()
    val showToast = SingleLiveEvent<String>()
    val showLoading = SingleLiveEvent<Boolean>()
    val showNoData = MutableLiveData<Boolean>()


}


