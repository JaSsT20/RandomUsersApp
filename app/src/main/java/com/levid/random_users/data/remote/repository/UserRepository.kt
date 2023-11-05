package com.levid.random_users.data.remote.repository

import com.levid.random_users.data.remote.ApiResponse
import com.levid.random_users.data.remote.RandomUserApi
import com.levid.random_users.data.remote.dto.UserDto
import com.levid.random_users.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val randomUserApi: RandomUserApi
) {
    suspend fun getUsers(): Flow<Resource<ApiResponse<List<UserDto>>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val users = randomUserApi.getUsers()

            emit(Resource.Success(users)) //indicar que tenemos los datos
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "Verifica tu conexion a internet"))
        }
    }
}