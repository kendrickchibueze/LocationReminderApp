package com.udacity.project4.locationreminders.geofence

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    companion object {
        const val ACTION_GEOFENCE_EVENT = "ACTION_GEOFENCE_EVENT"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == ACTION_GEOFENCE_EVENT) {
            handleGeofenceEvent(context, intent)
        }
    }

    private fun handleGeofenceEvent(context: Context, intent: Intent) {
        Log.i("GeofenceReceiver", "Geofence event received")
        GeofenceTransitionsJobIntentService.enqueueWork(context, intent)
    }
}
