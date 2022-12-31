package com.visualprogrammingclass.boncal.helpers

import com.google.gson.Gson

abstract class JsonConvertible {
    fun toJson(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        fun <T : JsonConvertible> fromJson(json: String, clazz: Class<T>): T {
            val gson = Gson()
            return gson.fromJson(json, clazz)
        }
    }
}