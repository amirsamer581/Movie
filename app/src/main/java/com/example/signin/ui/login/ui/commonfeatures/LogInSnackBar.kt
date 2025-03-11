package com.example.signin.ui.login.ui.commonfeatures

import android.content.res.ColorStateList
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.example.signin.R
import com.example.signin.ui.commonfeatures.SignInSnackBar
import com.google.android.material.snackbar.Snackbar


/**
 *  LogInSnackBar class is responsible for displaying SnackBar messages
 *  related to the login process, indicating success or error.
 *  It extends SignInSnackBar, inheriting its interface for showing messages.
 */
class LogInSnackBar: SignInSnackBar {

    override fun showSuccess(view: View, message: String) {
        try {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.view.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.green))
            snackbar.show()
        }catch (e : Exception){
            e.printStackTrace()
            Log.d("LogInSnackBar", "Error in showSuccess SnackBar: ${e.message.toString()}")
        }
    }

    override fun showError(view: View, message: String) {
        try {
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            snackbar.view.backgroundTintList =
                ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.red))
            snackbar.show()
        }catch (e : Exception){
            e.printStackTrace()
            Log.d("LogInSnackBar", "Error in showError SnackBar: ${e.message.toString()}")
        }
    }
}