package com.visualprogrammingclass.boncal.helpers

import android.content.Context
import android.util.Log
import android.widget.Toast

class httpCodeResponder(val code: Int, val context: Context, private val logTag: String) {

    private var errorCallback: () -> Unit = {}
    private var successCallback: () -> Unit = {}

    fun ifError(callback: () -> Unit): httpCodeResponder {
        errorCallback = callback
        return this
    }

    fun ifSuccess(callback: () -> Unit): httpCodeResponder {
        successCallback = callback
        return this
    }

    fun respond() {
        Log.e(logTag, code.toString())

        var message = ""
        when (code) {
            401 -> {
                message = "Token Expired"
                errorCallback()
            }
            500 -> {
                message = "Server Error"
                errorCallback()
            }
            200 -> {
                message = ""
                errorCallback()
            }

            else -> {
                message = "Error Unknown"
                errorCallback()
            }
        }

        if(message.isNotEmpty())
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
