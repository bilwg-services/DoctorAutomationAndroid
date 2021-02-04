package com.bilwg.doctorautomation.utils

import com.bilwg.doctorautomation.api.HospitalAPI
import com.bilwg.doctorautomation.deserializers.HospitalDeserializer
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private val BASE_URL = "http://api.myvirtualcards.in/";

    companion object {
        val instance = RetrofitClient()
    }

    val hospitalAPI: HospitalAPI by lazy {
        val gson = GsonBuilder()
            .registerTypeAdapter(HospitalDeserializer::class.java, HospitalDeserializer())
            .create()
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(HospitalAPI::class.java)
    }

}