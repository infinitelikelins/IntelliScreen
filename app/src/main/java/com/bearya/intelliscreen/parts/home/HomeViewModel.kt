package com.bearya.intelliscreen.parts.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bearya.intelliscreen.data.bean.HomeMenus
import com.bearya.intelliscreen.library.tool.Storage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    val list: MutableList<HomeMenus> = mutableListOf()

    fun init() {

        val menus = File(Storage.getUsbDir(getApplication()) + File.separator + "IntelliScreen" + File.separator + "menus.json")

        if (menus.exists()) {
            try {
                val menusList = Gson().fromJson<List<HomeMenus>>(FileReader(menus), object : TypeToken<List<HomeMenus>>() {}.type)
                list.clear()
                list.addAll(menusList)
            } catch (ex: Exception) {
                list.clear()
            }
        }
    }

}