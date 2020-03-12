package com.bilwg.doctorautomation.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.GeoPoint

@Suppress("UNCHECKED_CAST")
data class User(
    val id:String,
    val name:String,
    val email:String,
    val dob:Timestamp,
    val phoneNumber:String,
    val address:ArrayList<String>,
    val geoLocation:GeoPoint,
    val height:Double?,
    val weight:Double?,
    val photoURL:String?
){
    companion object{
        fun from(snapshot: DocumentSnapshot) : User{
            return User(
                id = snapshot.id,
                name = snapshot.getString("Name")!!,
                email = snapshot.getString("Email")!!,
                dob =snapshot.getTimestamp("DOB")!!,
                phoneNumber = snapshot.getString("PhoneNumber")!!,
                address =snapshot.get("Address")!! as ArrayList<String>,
                geoLocation =snapshot.getGeoPoint("LatLang")!!,
                height = snapshot.getDouble("Height"),
                weight = snapshot.getDouble("Weight"),
                photoURL = snapshot.getString("PhotoURL")
            )
        }
    }
}