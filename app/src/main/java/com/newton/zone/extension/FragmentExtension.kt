package com.newton.zone.extension

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun showDialogMessage(title: String, message: String, context: Context) {
    val builder = MaterialAlertDialogBuilder(context)
    with(builder) {
        setTitle(title)
        setMessage(message)
        setPositiveButton("OK", null)
        show()
    }

}