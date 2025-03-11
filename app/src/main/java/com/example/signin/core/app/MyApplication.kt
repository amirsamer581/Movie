package com.example.signin.core.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()
// will have the base class for the application and it acts as the application-level dependency container.