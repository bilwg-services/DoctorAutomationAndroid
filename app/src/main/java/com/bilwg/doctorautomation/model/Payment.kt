package com.bilwg.doctorautomation.model

data class Payment(
    val beneficiaryAN: String,
    val beneficiaryName: String,
    val ifsc: String,
    val paymentCycle: String
)