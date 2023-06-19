package com.code.myapplication.util

import android.content.SharedPreferences
import java.util.*
import javax.inject.Inject

class SettingsRepositories @Inject constructor(private val pref: SharedPreferences) {
    companion object {
        const val LANGUAGE = "language"
        const val ARABIC = "ar"
        const val ENGLISH = "en"
    }

    fun getLanguage(): String {
        val language = pref.getString(LANGUAGE, null)
        language?.let {
            return language
        }

        val localLanguage = Locale.getDefault().language
        return if (localLanguage != ARABIC && localLanguage != ENGLISH) ENGLISH
        else localLanguage
    }

    fun updateLanguage(language: String) = pref.edit().putString(LANGUAGE, language).commit()
}