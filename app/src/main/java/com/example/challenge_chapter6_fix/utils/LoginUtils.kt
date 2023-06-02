package com.example.challenge_chapter6_fix.utils

object LoginUtils {

    private val existingEmail = listOf("aaaa@gmail.com", "bbbb@gmail.com", "cccc@gmail.com", "dddd@gmail.com")

    fun validate(email : String, password : String) : Boolean{
        if (email.isEmpty()) return false
        if (!email.contains("@")) return false
        if (email.filter { it.isDigit() }.isEmpty()) return false

        if(password.isEmpty()) return false
        if(password.length<6)return false
        if(password.length>50)return false

        if(email in existingEmail) return false

        return true
    }

}