package com.udacity.project4.locationreminders.reminderslist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udacity.project4.base.BaseViewModel
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import kotlinx.coroutines.launch

class RemindersListViewModel(
    app: Application,
    private val dataSource: ReminderDataSource
) : BaseViewModel(app) {

    val remindersList = MutableLiveData<List<ReminderDataItem>>()


    fun loadReminders() {
        showLoading.value = true
        viewModelScope.launch {
            val result = getRemindersFromDataSource()
            showLoading.postValue(false)
            handleRemindersResult(result)
            invalidateShowNoData()
        }
    }

    private suspend fun getRemindersFromDataSource(): Result<List<ReminderDTO>> {
        return dataSource.getReminders()
    }

    private fun handleRemindersResult(result: Result<List<ReminderDTO>>) {
        when (result) {
            is Result.Success<*> -> {
                val dataList = ArrayList<ReminderDataItem>()
                dataList.addAll((result.data as List<ReminderDTO>).map { reminder ->

                    ReminderDataItem(
                        reminder.title,
                        reminder.description,
                        reminder.location,
                        reminder.latitude,
                        reminder.longitude,
                        reminder.id
                    )
                })
                remindersList.value = dataList
            }
            is Result.Error -> showSnackBar.value = result.message
        }
    }

    private fun invalidateShowNoData() {
        showNoData.value = remindersList.value.isNullOrEmpty()
    }

   /* fun getRemindersList(): LiveData<List<ReminderDataItem>> {
        return remindersList
    }*/

}