package com.code.myapplication.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @ColumnInfo(name = "full_name") val fullName: String,

    @PrimaryKey @ColumnInfo(name = "email") val email: String,

    @ColumnInfo(name = "national_id") val nationalId: String,

    @ColumnInfo(name = "phone_number") val phoneNumber: String,

    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String,

    @ColumnInfo(name = "password") val password: String,

    @ColumnInfo(name = "date_of_registration") val dateOfRegistration: String

)