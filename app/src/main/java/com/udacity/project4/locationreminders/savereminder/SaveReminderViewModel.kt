package com.udacity.project4.locationreminders.savereminder

import android.app.Application
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.PointOfInterest
import com.udacity.project4.R
import com.udacity.project4.base.BaseViewModel
import com.udacity.project4.base.NavigationCommand
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem
import kotlinx.coroutines.launch




class SaveReminderViewModel(val app: Application, val dataSource: ReminderDataSource) :
    BaseViewModel(app) {
    val reminderTitle = MutableLiveData<String>()
    val reminderDescription = MutableLiveData<String>()
    val reminderSelectedLocationStr = MutableLiveData<String>()
    val selectedPOI = MutableLiveData<PointOfInterest>()
    val latitude = MutableLiveData<Double>()
    val longitude = MutableLiveData<Double>()

    fun onClear() {
        listOf(reminderTitle, reminderDescription, reminderSelectedLocationStr, selectedPOI, latitude, longitude)
            .forEach { it.value = null }
    }

    fun validateAndSaveReminder(reminderData: ReminderDataItem) {
        if (validateEnteredData(reminderData)) {
            saveReminder(reminderData)
        }
    }

    fun saveReminder(reminderData: ReminderDataItem) {
        showLoading.value = true
        viewModelScope.launch {
            saveReminderToDataSource(reminderData)
        }
    }

    private suspend fun saveReminderToDataSource(reminderData: ReminderDataItem) {
        dataSource.saveReminder(
            ReminderDTO(
                reminderData.title,
                reminderData.description,
                reminderData.location,
                reminderData.latitude,
                reminderData.longitude,
                reminderData.id
            )
        )
        showLoading.value = false
        showToast.value = app.getString(R.string.reminder_saved)
        navigationCommand.value = NavigationCommand.Back
    }

    fun validateEnteredData(reminderData: ReminderDataItem): Boolean {
        return when {
            reminderData.title.isNullOrEmpty() -> {
                showSnackBarInt.value = R.string.err_enter_title
                false
            }
            reminderData.location.isNullOrEmpty() -> {
                showSnackBarInt.value = R.string.err_select_location
                false
            }
            else -> true
        }
    }
}
