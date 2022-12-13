package com.visualprogrammingclass.boncal.viewModels

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import com.visualprogrammingclass.boncal.repositories.DataStoreRepository

class DataStoreViewModel(context: Context): AndroidViewModel(Application()) {



    fun createToast(){
        Toast.makeText(Application(), "Dari DataStore", Toast.LENGTH_SHORT).show()
    }



}