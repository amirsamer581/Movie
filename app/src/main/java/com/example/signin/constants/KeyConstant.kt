package com.example.signin.constants

/**
 *  KeyConstant
 *
 *  This object holds constant values used throughout the application.
 *  It serves as a centralized repository for keys, URLs, messages, and other
 *  static data that needs to be accessed from multiple parts of the application.
 *
 *  By using constants, we avoid magic strings and make the code more maintainable.
 *  If a value needs to be changed, it only needs to be updated here, rather than
 *  in every place it is used.
 */
object KeyConstant {

        // MovieDataBaseModule
        const val MOVIE_BASE_URL = "https://dummyapi.online/api/"

        // MovieDetail Fragment
        const val MOVIE_DETAIL = "movie_detail"

        // Home Fragment
        const val ACTION_MOVIE = "Action Movies"
        const val COMEDY_MOVIE = "Comedy Movies"
        const val ADVENTURE_MOVIE = "Adventure Movies"
        const val DRAMA_MOVIE  = "Drama Movies"

        // Login Fragment and Sign up Fragment
        const val FIRST_NAME = "Please enter your first name"
        const val LAST_NAME = "Please enter your last name"
        const val ENTER_EMAIL = "Please enter your email"
        const val ENTER_PASSWORD = "Please enter your password"
        const val DATE_OF_BIRTH = "Please enter your date of birth"
        const val INVALID_USER = "Invalid email or password !!"
        const val SIGN_UP_SUCCESSFUL = "Sign up successful"
        const val LOG_IN_SUCCESSFUL = "Log in successful"

        // DataStoreRepositoryImpl for the Login Fragment
        const val USER_PREFERENCES = "user_preferences"

        // DataStoreUseCase for the Login Fragment
        const val KEY_USER_EMAIL = "user_email"
        const val KEY_USER_PASSWORD = "user_password"

        // ImportObject Fragment
        const val LOCATION_NOT_ALLOW = "Please allow the location !!"
}