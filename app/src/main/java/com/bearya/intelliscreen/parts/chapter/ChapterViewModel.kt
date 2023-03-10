package com.bearya.intelliscreen.parts.chapter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bearya.intelliscreen.data.bean.MenuChapter
import com.bearya.intelliscreen.library.tool.Storage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileReader

class ChapterViewModel(app: Application) : AndroidViewModel(app) {

    val list: MutableLiveData<MutableList<MenuChapter>> by lazy { MutableLiveData() }

    fun init(fileDir: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val chapterFile = File(Storage.getUsbDir(getApplication()) + fileDir)

            if (chapterFile.exists()) {
                try {
                    val chapterList = Gson().fromJson<MutableList<MenuChapter>>(FileReader(chapterFile), object : TypeToken<List<MenuChapter>>() {}.type)
                    list.postValue(chapterList)
                } catch (ex: java.lang.Exception) {
                    list.postValue(mutableListOf())
                }
            } else list.postValue(mutableListOf())
        }
    }

}