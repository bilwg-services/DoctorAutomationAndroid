package com.bilwg.doctorautomation.model

data class Address(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val geoLocation: GeoLocation,
    val pinCode: String,
    val state: String
)