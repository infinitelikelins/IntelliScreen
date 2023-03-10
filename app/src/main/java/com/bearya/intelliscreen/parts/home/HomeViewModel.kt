package com.bearya.intelliscreen.parts.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bearya.intelliscreen.data.bean.HomeMenus
import com.bearya.intelliscreen.library.tool.Storage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileReader

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    val list: MutableLiveData<MutableList<HomeMenus>> by lazy { MutableLiveData() }

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val menus = File(Storage.getUsbDir(getApplication()) + File.separator + "IntelliScreen" + File.separator + "menus.json")

            if (menus.exists()) {
                try {
                    val menusList = Gson().fromJson<MutableList<HomeMenus>>(FileReader(menus), object : TypeToken<List<HomeMenus>>() {}.type)
                    list.postValue(menusList)
                } catch (ex: Exception) {
                    list.postValue(mutableListOf())
                }
            } else list.postValue(mutableListOf())
        }
    }

}