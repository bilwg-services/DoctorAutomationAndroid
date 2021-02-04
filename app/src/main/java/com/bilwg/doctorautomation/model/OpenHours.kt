package com.bilwg.doctorautomation.model

data class OpenHours(
    val friday: Map<String, Any>,
    val monday: Map<String, Any>,
    val saturday: Map<String, Any>,
    val sunday: Map<String, Any>,
    val thusday: Map<String, Any>,
    val tuesday: Map<String, Any>,
    val wednesday: Map<String, Any>
)