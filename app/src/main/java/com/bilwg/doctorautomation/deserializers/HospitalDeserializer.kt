package com.bilwg.doctorautomation.deserializers

import com.bilwg.doctorautomation.model.Hospital
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class HospitalDeserializer : JsonDeserializer<Hospital> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Hospital {
        val content: JsonElement = json!!.asJsonObject.get("data")
        return Gson().fromJson(content, Hospital::class.java)
    }

}