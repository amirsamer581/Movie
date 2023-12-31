package com.example.signin.commonfeatures

import android.view.View

interface SignInSnackBar {
    fun showSuccess(view: View, message: String)
    fun showError(view: View, message: String)
    fun allFields(view: View, message: String)
}