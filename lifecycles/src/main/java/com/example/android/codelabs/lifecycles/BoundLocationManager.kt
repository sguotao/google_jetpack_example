package com.example.android.codelabs.lifecycles

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class BoundLocationManager {

    companion object {
        fun bindLocationListenerIn(
            lifecycleOwner: LifecycleOwner,
            listener: LocationListener,
            context: Context
        ) {
            BoundLocationListener(lifecycleOwner, listener, context)
        }
    }

    @SuppressWarnings("MissingPermission")
    class BoundLocationListener(
        var lifecycleOwner: LifecycleOwner,
        var mListener: LocationListener,
        var mContext: Context
    ) : LifecycleObserver {

        private var mLocationManager: LocationManager? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun addLocationListener() {
            // Note: Use the Fused Location Provider from Google Play Services instead.
            // https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderApi
            mLocationManager =
                mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            mLocationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                0L,
                0f,
                mListener
            )
            Log.d("BoundLocationMgr", "Listener added")

            // Force an update with the last location, if available.
            val lastLocation = mLocationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            lastLocation?.let {
                mListener.onLocationChanged(lastLocation)
            }

        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun removeLocationListener() {
            mLocationManager?.let {
                it.removeUpdates(mListener)
            }
            mLocationManager = null
            Log.d("BoundLocationMgr", "Listener removed");
        }
    }
}