package com.bilwg.doctorautomation.model

data class Hospital(
    val __v: Int,
    val _id: String,
    val address: Address,
    val createdAt: String,
    val descriptions: String,
    val email: String,
    val images: List<String>,
    val logo: String,
    val name: String,
    val openHours: OpenHours,
    val parking: String,
    val payment: Payment,
    val phone: String,
    val specializations: List<String>,
    val status: Int,
    val tagline: String,
    val updatedAt: String,
    val website: String
)