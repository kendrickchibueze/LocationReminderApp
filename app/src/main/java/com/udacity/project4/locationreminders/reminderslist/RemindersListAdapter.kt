package com.udacity.project4.locationreminders.reminderslist

import com.udacity.project4.R
import com.udacity.project4.base.BaseRecyclerViewAdapter



class RemindersListAdapter(private val callback: (selectedReminder: ReminderDataItem) -> Unit) :
    BaseRecyclerViewAdapter<ReminderDataItem>(callback) {

    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.it_reminder
    }
}
