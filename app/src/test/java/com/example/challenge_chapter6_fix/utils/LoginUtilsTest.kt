package com.example.challenge_chapter6_fix.utils

import junit.framework.Assert.assertFalse
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert
import org.junit.Before

import org.junit.Test

class LoginUtilsTest {

    lateinit var login : LoginUtils

    @Before
    fun setUp() {
        login = LoginUtils
    }

    @After
    fun tearDown() {
    }

    @Test
    fun email_is_empty() {
        val actualResult = login.validate("", "saifenjfewo")
        assertFalse(actualResult)
    }

    @Test
    fun email_length_less_than_6() {
        val actualResult = login.validate("abcd", "saifenjfewo")
        assertFalse(actualResult)
    }

    @Test
    fun email_length_very_long(){
        val actualResult = login.validate("aaaaaaaaaaaaaaaaaaaa", "saifenjfewo")
        assertFalse(actualResult)
    }

    @Test
    fun existingEmail(){
        val actualResult = login.validate("aaaa@gmail.com", "dkjafjla")
        assertFalse(actualResult)
    }

    @Test
    fun passwordToSort(){
        val actualResult = login.validate("fewnkewm@gmail.com", "a")
        assertFalse(actualResult)
    }
    @Test
    fun passwordToLong(){
        val actualResult = login.validate("fewnkewm@gmail.com", "sjadknainfweaaaaaaaaaaaaaaa")
        assertFalse(actualResult)
    }
}