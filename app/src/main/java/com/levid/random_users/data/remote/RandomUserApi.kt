package com.levid.random_users.data.remote

import com.levid.random_users.data.remote.dto.UserDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserApi {
    @GET("api/?gender=female&?page=3&results=10&seed=abc")
    suspend fun getUsers(): ApiResponse<List<UserDto>>
}