package com.bearya.intelliscreen.parts.chapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.bearya.intelliscreen.data.bean.MenuChapter
import com.bearya.intelliscreen.library.tool.StorageTool
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileReader

class ChapterViewModel(app: Application) : AndroidViewModel(app) {

    val list: MutableList<MenuChapter> = mutableListOf()

    fun init(fileDir: String) {
        val chapterFile = File(StorageTool.getUsbDir(getApplication()) + fileDir)

        if (chapterFile.exists()) {
            val chapterList = Gson().fromJson<List<MenuChapter>>(FileReader(chapterFile), object : TypeToken<List<MenuChapter>>() {}.type)
            list.addAll(chapterList)
        }
    }

}