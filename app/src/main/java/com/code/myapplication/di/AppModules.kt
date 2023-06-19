package com.code.myapplication.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.code.myapplication.data.local.UserDao
import com.code.myapplication.data.local.UserDatabase
import com.code.myapplication.data.remote.AppService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModules {
    companion object {
        const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
        val JSON_MEDIA_TYPE = "application/json".toMediaType()
    }

    private val json = Json {
        this.ignoreUnknownKeys = true
        this.coerceInputValues = true
    }

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDao {
        return Room.databaseBuilder(app, UserDatabase::class.java, "user_db").build().dao
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("name", MODE_PRIVATE)
    }


    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE))
        .build()

    @Provides
    fun provideAPPService(retrofit: Retrofit): AppService =
        retrofit.create(AppService::class.java)
}