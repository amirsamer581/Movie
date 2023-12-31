package com.example.signin.ui.signup.commonfeatures

import android.view.View
import androidx.core.content.ContextCompat
import com.example.signin.R
import com.example.signin.commonfeatures.SignInSnackBar
import com.google.android.material.snackbar.Snackbar

open class SignUpSnackBar : SignInSnackBar {
    override fun showSuccess(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.black))
        snackbar.show()
    }

    override fun showError(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.ANIMATION_MODE_FADE)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.green))
        snackbar.show()
    }

    override fun allFields(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.blue))
        snackbar.show()
    }
}