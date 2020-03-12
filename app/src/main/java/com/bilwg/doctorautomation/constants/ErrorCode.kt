package com.bilwg.doctorautomation.constants

class ErrorCode {

    companion object{
        /*****************
        * AuthService.kt *
        ******************/
        //Email Auth
        const val AUTH_FAILED = 1
        const val AUTH_FAILED_ON_SUCCESS = 2
        const val NOT_GET_USER = 3

        //Get User
        const val CANNOT_GET_USER_FROM_DB = 4
    }

}