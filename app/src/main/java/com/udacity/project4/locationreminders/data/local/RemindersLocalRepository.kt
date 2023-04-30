package com.udacity.project4.locationreminders.data.local

import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.dto.ReminderDTO
import com.udacity.project4.locationreminders.data.dto.Result
import com.udacity.project4.utils.EspressoIdlingResource.wrapEspressoIdlingResource
import kotlinx.coroutines.*


class RemindersLocalRepository(
    private val remindersDao: RemindersDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ReminderDataSource {



    override suspend fun getReminders(): Result<List<ReminderDTO>> = withIOContext {
        wrapEspressoIdlingResource {
            try {
                Result.Success(remindersDao.getReminders())
            } catch (ex: Exception) {
                Result.Error(ex.localizedMessage)
            }
        }
    }

    override suspend fun saveReminder(reminder: ReminderDTO) = withIOContext {
        wrapEspressoIdlingResource {
            remindersDao.saveReminder(reminder)
        }
    }

    override suspend fun getReminder(id: String): Result<ReminderDTO> = withIOContext {
        wrapEspressoIdlingResource {
            val reminder = remindersDao.getReminderById(id)
            if (reminder != null) {
                Result.Success(reminder)
            } else {
                Result.Error("Reminder not found!")
            }
        }
    }

    private suspend fun <T> withIOContext(block: suspend CoroutineScope.() -> T): T =
        withContext(ioDispatcher, block)

    override suspend fun deleteAllReminders() {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                remindersDao.deleteAllReminders()
            }
        }

    }
}
