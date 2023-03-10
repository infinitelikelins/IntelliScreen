package com.bearya.intelliscreen.parts.content

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.bearya.intelliscreen.data.bean.ChapterPage
import com.bearya.intelliscreen.library.ext.setData
import com.bearya.intelliscreen.library.tool.Storage
import com.bearya.intelliscreen.parts.content.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileReader

class PagerViewModel(app: Application) : AndroidViewModel(app) {

    private val playList: MutableList<ChapterPage> = mutableListOf()

    private val playIndex: MutableLiveData<Int> = MutableLiveData<Int>()

    val currentFragment: LiveData<Fragment?> = playIndex.map {
        if (playList.isEmpty()) return@map EmptyFragment.newInstance()
        else if (it >= 0) {
            val item: ChapterPage = playList[it]
            when {
                item.A != null -> AFragment.newInstance(item.A)
                item.B != null -> BFragment.newInstance(item.B)
                item.C != null -> CFragment.newInstance(item.C)
                item.D != null -> DFragment.newInstance(item.D)
                item.E != null -> EFragment.newInstance(item.E)
                item.F != null -> FFragment.newInstance(item.F)
                item.G != null -> GFragment.newInstance(item.G)
                item.H != null -> HFragment.newInstance(item.H)
                item.I != null -> IFragment.newInstance(item.I)
                item.J != null -> JFragment.newInstance(item.J)
                else -> EmptyFragment.newInstance()
            }
        } else EmptyFragment.newInstance()
    }

    fun init(file: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val menus = File(Storage.getUsbDir(getApplication()) + file)

            if (menus.exists()) {
                try {
                    val menusList = Gson().fromJson<List<ChapterPage>>(FileReader(menus), object : TypeToken<List<ChapterPage>>() {}.type)
                    playList.clear()
                    playList.addAll(menusList)
                    playIndex.postValue(0)
                } catch (ex: java.lang.Exception) {
                    playList.clear()
                }
            } else playIndex.postValue(-1)

        }
    }

    fun next() {
        if (playList.isNotEmpty()) {
            playIndex.value?.takeIf { it < (playList.size - 1) }
                ?.plus(1)
                ?.apply { playIndex.setData(this) }
        }
    }

    fun up() {
        if (playList.isNotEmpty()) {
            playIndex.value?.takeIf { it > 0 }
                ?.minus(1)
                ?.apply { playIndex.setData(this) }
        }
    }


}