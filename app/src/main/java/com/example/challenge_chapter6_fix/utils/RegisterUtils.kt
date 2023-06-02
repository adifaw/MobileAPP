package com.example.challenge_chapter6_fix.utils

object RegisterUtils {

    private val existingUser = listOf("abcd", "efgh", "ijkl", "mnop")

    fun validate(username: String, name: String, email: String, birthday: String, nomor: String, password : String, confirmPass: String) : Boolean{
        if (username.isEmpty()) return false
        if (username.length<6) return false
        if(username.length>20)return false

        if (name.isEmpty()) return false
        if (name.length<6) return false

        if (!email.contains("@")) return false
        if (email.filter { it.isDigit() }.isEmpty()) return false

        if (birthday.isEmpty()) return false
        if (!birthday.contains("/") and !birthday.contains("0123456789")) return false

        if (nomor.isEmpty()) return false
        if (nomor.length != 12) return false

        if(password.isEmpty()) return false
        if(password.length<6)return false
        if(password.length>20)return false

        if(username in existingUser) return false

        if(password != confirmPass) return false

        return true
    }

}