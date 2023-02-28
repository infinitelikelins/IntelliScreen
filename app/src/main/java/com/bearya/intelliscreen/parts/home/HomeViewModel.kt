package com.bearya.intelliscreen.parts.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.bearya.intelliscreen.data.bean.HomeMenus
import com.bearya.intelliscreen.library.tool.StorageTool
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader

class HomeViewModel(app: Application) : AndroidViewModel(app) {

    val list: MutableList<HomeMenus> = mutableListOf()

    fun init() {

        val menus = File(StorageTool.getUsbDir(getApplication()) + File.separator + "IntelliScreen" + File.separator + "menus.json")

        val menusList = Gson().fromJson<List<HomeMenus>>(FileReader(menus), object : TypeToken<List<HomeMenus>>() {}.type)

        list.addAll(menusList)

//        list.add(HomeMenus(1, "rhythm", "节奏", R.drawable.icon_rhythm, "rhythm.json"," R.drawable.chapter_rhythm", "R.drawable.container_rhythm"))
//        list.add(HomeMenus(2, "melody", "旋律", R.drawable.icon_melody, "melody.json", "R.drawable.chapter_melody", "R.drawable.container_rhythm"))
//        list.add(HomeMenus(3, "beat", "节拍", R.drawable.icon_beat, "beat.json", R.drawable.chapter_beat, R.drawable.container_rhythm))
//        list.add(HomeMenus(4, "structure", "曲式结构", R.drawable.icon_structure, "structure.json", R.drawable.chapter_structure, R.drawable.container_rhythm))
//        list.add(HomeMenus(5, "speed", "力度速度", R.drawable.icon_speed, "speed.json", R.drawable.chapter_speed, R.drawable.container_rhythm))
//        list.add(HomeMenus(6, "timbre", "音色", R.drawable.icon_timbre, "timbre.json", R.drawable.chapter_timbre, R.drawable.container_rhythm))

    }

}