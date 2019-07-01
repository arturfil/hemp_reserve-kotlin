package com.arturofilio.hemp_reserve_mvvm.util

import android.content.Context
import android.support.v4.widget.CircularProgressDrawable

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

// image view func ->