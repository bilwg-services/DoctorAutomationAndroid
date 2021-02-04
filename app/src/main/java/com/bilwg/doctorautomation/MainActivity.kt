package com.bilwg.doctorautomation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bilwg.doctorautomation.service.HospitalsService

class MainActivity : AppCompatActivity() {

    val hospitalsService = HospitalsService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hospitalsService.getAllHospitals { hospitals, error ->
            if (error != null) {
                Log.e("MainActivity", error)
            }
            Log.d("MainActivity", hospitals.toString())
        }
    }
}
