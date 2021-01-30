package com.example.themoviedb_android.utils.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showLongToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Context.showLongToast(@StringRes resourceId: Int){
    Toast.makeText(this, resourceId, Toast.LENGTH_LONG).show()
}