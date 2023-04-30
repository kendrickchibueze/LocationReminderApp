package com.udacity.project4.locationreminders.geofence

import android.content.Context
import android.content.res.Resources
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.udacity.project4.R

object GeofenceUtils {
    const val GEOFENCE_RADIUS_IN_METERS = 100f
    const val EXTRA_GEOFENCE_INDEX = "GEOFENCE_INDEX"
    const val NEVER_EXPIRES = Geofence.NEVER_EXPIRE
}


fun errorMessage(context: Context, errorCode: Int): String {
    val resources = context.resources
    return when (errorCode) {
        GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE -> getErrorMessage(
            resources,
            R.string.geofence_not_available
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES -> getErrorMessage(
            resources,
            R.string.geofence_too_many_geofences
        )
        GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS -> getErrorMessage(
            resources,
            R.string.geofence_too_many_pending_intents
        )
        else -> getErrorMessage(resources, R.string.unknown_geofence_error)
    }
}

private fun getErrorMessage(resources: Resources , stringResId: Int): String {
    return resources.getString(stringResId)
}
