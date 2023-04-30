package com.udacity.project4.locationreminders.data.local

import android.content.Context
import androidx.room.Room



    fun createRemindersDao(context: Context): RemindersDao {
        return Room.databaseBuilder(
            context.applicationContext,
            RemindersDatabase::class.java, "Reminders.db"
        ).build().reminderDao()
    }

