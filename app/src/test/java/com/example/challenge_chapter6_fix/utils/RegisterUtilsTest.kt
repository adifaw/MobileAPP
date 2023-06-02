package com.example.challenge_chapter6_fix.utils

import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class RegisterUtilsTest {

    lateinit var register : RegisterUtils

    @Before
    fun setUp() {
        register = RegisterUtils
    }

    @After
    fun tearDown() {
    }

    @Test
    fun username_validate(){
        val emptyResult = register.validate("", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val shortResult = register.validate("a", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val longResult = register.validate("hbfewnkmpwfeifnwoeinfwenofwniofwifwijnfweifweiio", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertFalse(emptyResult)
        assertFalse(shortResult)
        assertFalse(longResult)
    }

    @Test
    fun name_validate(){
        val emptyResult = register.validate("username", "", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val shortResult = register.validate("username", "a", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertFalse(emptyResult)
        assertFalse(shortResult)
    }

    @Test
    fun email_validate(){
        val notContainsResult = register.validate("username", "aiffah kiysa", "aaaab", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertFalse(notContainsResult)
    }

    @Test
    fun birthday_validate(){
        val emptyResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        val notContainsResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "abc", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertFalse(emptyResult)
        assertFalse(notContainsResult)
    }

    @Test
    fun nomor_validate(){
        val emptyResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "", "hjbfjewnijw", "hjbfjewnijw")
        val longResult = register.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "08213249877067890", "hjbfjewnijw", "hjbfjewnijw")
        assertFalse(emptyResult)
        assertFalse(longResult)
    }

    @Test
    fun existingUser(){
        val actualResult = RegisterUtils.validate("abcd", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfjewnijw", "hjbfjewnijw")
        assertFalse(actualResult)
    }

    @Test
    fun passwordToSort(){
        val actualResult = RegisterUtils.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "a", "a")
        assertFalse(actualResult)
    }
    @Test
    fun passwordToLong(){
        val actualResult = RegisterUtils.validate("username", "aiffah kiysa", "aaaa@gmail.com", "22/03/2004", "082132498770", "hjbfhbwejnofewiffjewerjewjnnijw", "hjbfhbwejnofewiffjewerjewjnnijw")
        assertFalse(actualResult)
    }


}