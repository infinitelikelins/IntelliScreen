package com.bearya.intelliscreen.parts.content

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.bearya.intelliscreen.data.bean.*
import com.bearya.intelliscreen.library.ext.setData
import com.bearya.intelliscreen.parts.content.model.*
import com.orhanobut.logger.Logger

class PagerViewModel : ViewModel() {

    private var playList: MutableList<ChapterPage> = mutableListOf()

    private val playIndex: MutableLiveData<Int> = MutableLiveData<Int>()

    val currentFragment: LiveData<Fragment?> = playIndex.map {
        if (playList.isEmpty()) return@map null
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
            else -> null
        }
    }

    fun init(file: String) {
        Logger.d(file)
        playList.add(ChapterPage(A = PageA("")))
        playList.add(ChapterPage(B = PageB("","")))
        playList.add(ChapterPage(C = PageC("")))
        playList.add(ChapterPage(D = PageD("")))
        playList.add(ChapterPage(E = PageE("")))
        playList.add(ChapterPage(F = PageF("")))
        playList.add(ChapterPage(G = PageG("")))
        playList.add(ChapterPage(H = PageH("")))
        playList.add(ChapterPage(I = PageI("")))
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