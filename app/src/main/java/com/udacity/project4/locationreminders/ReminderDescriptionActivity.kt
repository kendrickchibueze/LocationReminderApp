package com.udacity.project4.locationreminders

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.udacity.project4.R
import com.udacity.project4.databinding.ActivityReminderDescriptionBinding
import com.udacity.project4.locationreminders.reminderslist.ReminderDataItem


class ReminderDescriptionActivity : AppCompatActivity() {

    companion object {
        private const val EXTRA_ReminderDataItem = "EXTRA_ReminderDataItem"


        fun newIntent(context: Context, reminderDataItem: ReminderDataItem): Intent {
            return Intent(context, ReminderDescriptionActivity::class.java).apply {
                putExtra(EXTRA_ReminderDataItem, reminderDataItem)
            }
        }
    }

    private lateinit var binding: ActivityReminderDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupReminderData()
    }

    private fun setupBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reminder_description)
        binding.lifecycleOwner = this
    }

    private fun setupReminderData() {
        val reminderData = intent.extras?.get(EXTRA_ReminderDataItem) as ReminderDataItem
        binding.reminderDataItem = reminderData
    }

}
