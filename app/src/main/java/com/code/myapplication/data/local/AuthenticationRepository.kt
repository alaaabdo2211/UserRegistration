package com.code.myapplication.data.local


interface AuthenticationRepository {
    suspend fun insert(user: UserEntity)
    suspend fun getUser(): UserEntity?
    suspend fun isTaken(email: String): Boolean
    suspend fun login(email: String, password: String): Boolean


}