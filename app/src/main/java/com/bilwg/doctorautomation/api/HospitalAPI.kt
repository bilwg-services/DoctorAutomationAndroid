package com.bilwg.doctorautomation.api

import com.bilwg.doctorautomation.model.Hospital
import retrofit2.Call
import retrofit2.http.GET

interface HospitalAPI {

    @GET("hospital")
    fun getAllHospitals(): Call<List<Hospital>>

    @GET("hospital")
    fun getHospital(hospitalId: String): Call<Hospital>
}