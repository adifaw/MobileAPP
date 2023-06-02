package com.example.challenge_chapter6_fix.repository

import com.example.challenge_chapter6_fix.model.Item
import com.example.challenge_chapter6_fix.model.Movie
import com.example.challenge_chapter6_fix.service.ApiHelper
import com.example.challenge_chapter6_fix.service.ApiService
import io.mockk.Call
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class MainRepositoryTest {

    private lateinit var service: ApiService
    private lateinit var apiHelper: ApiHelper
    private lateinit var repo: MainRepository
    @Before
    fun setUp(){
        service = mockk()
        apiHelper = ApiHelper(service)
        repo = MainRepository(apiHelper)
    }
    @Test
    fun getUsers(): Unit = runBlocking {
        val respAllMovie = mockk<retrofit2.Call<Movie>>()

        every {
            runBlocking {
                service.getDetailFilm()
            }
        } returns respAllMovie

        repo.getUsers()

        verify {
            runBlocking { service.getDetailFilm() }
        }
    }
}