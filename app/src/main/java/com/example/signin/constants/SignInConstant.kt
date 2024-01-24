package com.example.signin.constants

import androidx.datastore.preferences.core.stringPreferencesKey

object SignInConstant {

        const val MOVIE_DETAIL = "movie_detail"

        const val BASE_URL = "https://dummyapi.online/api/"
        const val ACTION_MOVIE = "Action Movies"
        const val COMEDY_MOVIE = "Comedy Movies"
        const val ADVENTURE_MOVIE = "Adventure Movies"
        const val ENTER_EMAIL = "Please enter your email"
        const val ENTER_PASSWORD = "Please enter your password"
        const val INVALID_USER = "Invalid email or password!"
        const val FIRST_NAME = "Please enter your first name"
        const val LAST_NAME = "Please enter your last name"
        const val DATE_OF_BIRTH = "Please enter your date of birth"
        const val SIGN_UP_SUCCESSFUL = "Sign up successful"
        const val LOG_IN_SUCCESSFUL = "Log in successful"

        const val USER_PREFERENCES = "user_preferences"
        const val KEY_USER_EMAIL = "user_email"
        const val KEY_PASSWORD_EMAIL = "user_password"

        val KEY_USER_EMAIL1 = stringPreferencesKey("user_email")
        val KEY_PASSWORD_EMAIL1 = stringPreferencesKey("user_password")
}