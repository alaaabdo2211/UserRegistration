package com.code.myapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(register: UserEntity)

    @Query("SELECT * FROM users WHERE email=:email")
    suspend fun getUser(email: String): UserEntity?

    @Query("SELECT EXISTS (SELECT * from users WHERE email=:email)")
    suspend fun isTaken(email: String): Boolean

    @Query("SELECT EXISTS (SELECT * from users WHERE email=:email AND password=:password)")
    suspend fun login(email: String, password: String): Boolean

}