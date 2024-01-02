package com.example.signin.ui.signup.commonfeatures

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import com.example.signin.R
import com.example.signin.commonfeatures.SignInSnackBar
import com.google.android.material.snackbar.Snackbar

open class SignUpSnackBar : SignInSnackBar {
    override fun showSuccess(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.green))
        snackbar.show()
    }

    override fun showError(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.ANIMATION_MODE_FADE)
        snackbar.view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.red))
        snackbar.show()
    }

    override fun allFields(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, R.color.blue))
        snackbar.show()
    }
}