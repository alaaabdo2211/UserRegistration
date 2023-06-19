package com.code.myapplication.domain.repositories

import android.content.SharedPreferences
import com.code.myapplication.data.local.AuthenticationRepository
import com.code.myapplication.data.local.UserDao
import com.code.myapplication.data.local.UserEntity
import javax.inject.Inject

class AuthenticationRepoImpl @Inject constructor(
    private val dao: UserDao, private val sharedPref: SharedPreferences
) : AuthenticationRepository {

    override suspend fun insert(user: UserEntity) {
        val editor = sharedPref.edit()
        editor.putString("email", user.email)
        editor.putString("password", user.password)
        editor.putBoolean("isLoggedIn", true)
        editor.apply()
        return dao.insert(user)
    }

    override suspend fun getUser(): UserEntity? {
        val editor = sharedPref.getString("email", "")
        return dao.getUser(editor.toString())
    }

    override suspend fun isTaken(email: String): Boolean {
        return dao.isTaken(email = email)
    }

    override suspend fun login(email: String, password: String): Boolean {
        val loginResult = dao.login(email, password)
        if (loginResult) {
            val editor = sharedPref.edit()
            editor.putString("email", email)
            editor.putString("password", password)
            editor.putBoolean("isLoggedIn", true)
            editor.apply()
        }

        return loginResult

    }


}
