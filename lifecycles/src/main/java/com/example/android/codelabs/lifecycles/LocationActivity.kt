package com.example.android.codelabs.lifecycles

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.location_activity.tv_location

class LocationActivity : AppCompatActivity() {

    val mGpsListener = MyLocationListener()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 1 &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED &&
            grantResults[1] == PackageManager.PERMISSION_GRANTED
        ) {
            bindLocationListener()
        } else {
            Toast.makeText(this, "This sample requires Location access", Toast.LENGTH_LONG).show()
        }
    }

    private fun bindLocationListener() {
        BoundLocationManager.bindLocationListenerIn(this, mGpsListener, applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_activity)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_LOCATION_PERMISSION_CODE
            )
        } else {
            bindLocationListener()
        }
    }

    companion object {
        const val REQUEST_LOCATION_PERMISSION_CODE = 1
    }

    inner class MyLocationListener : LocationListener {
        override fun onLocationChanged(location: Location?) {
            tv_location.text = "${location?.latitude} , ${location?.longitude}"
        }

        override fun onProviderDisabled(provider: String?) {

        }

        override fun onProviderEnabled(provider: String?) {
            Toast.makeText(
                this@LocationActivity,
                "Provider enabled: $provider", Toast.LENGTH_SHORT
            ).show()
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

        }
    }
}