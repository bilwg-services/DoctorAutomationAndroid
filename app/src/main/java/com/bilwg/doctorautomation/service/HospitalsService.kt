package com.bilwg.doctorautomation.service

import com.bilwg.doctorautomation.model.Hospital
import com.bilwg.doctorautomation.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalsService {

    private val hospitalAPI = RetrofitClient.instance.hospitalAPI

    fun getAllHospitals(callback: (hospitals: List<Hospital>?, error: String?) -> Unit) {
        hospitalAPI.getAllHospitals().enqueue(object : Callback<List<Hospital>> {
            override fun onFailure(call: Call<List<Hospital>>?, t: Throwable?) {
                callback(null, t?.message ?: "Something went wrong")
            }

            override fun onResponse(
                call: Call<List<Hospital>>?,
                response: Response<List<Hospital>>?
            ) {
                if (response != null && response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, "Something went wrong")
                }
            }
        })

    }

}