package com.bearya.intelliscreen.parts.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.HomeMenus
import com.bearya.intelliscreen.databinding.FragmentHomeBinding

// 首页菜单
class HomeFragment : Fragment() {

    private lateinit var bindView: FragmentHomeBinding

    private val menusAdapter: MenusAdapter by lazy { MenusAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentHomeBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindView.menus.layoutManager = GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
        bindView.menus.itemAnimator = DefaultItemAnimator()
        bindView.menus.adapter = menusAdapter

        menusAdapter.setOnItemClickListener { _, v, position ->

            val item = menusAdapter.getItem(position)
            val directions = HomeFragmentDirections.actionHomeFragmentToChapterFragment(item.name, item.file, item.subTitle , item.subContainer)
            Navigation.findNavController(v).navigate(directions)

        }

        menusAdapter.addData(HomeMenus(1, "rhythm", "节奏", R.drawable.icon_rhythm, "rhythm.json", R.drawable.chapter_rhythm, R.drawable.container_rhythm))
        menusAdapter.addData(HomeMenus(2, "melody", "旋律", R.drawable.icon_melody, "melody.json", R.drawable.chapter_melody, R.drawable.container_rhythm))
        menusAdapter.addData(HomeMenus(3, "beat", "节拍", R.drawable.icon_beat, "beat.json", R.drawable.chapter_beat, R.drawable.container_rhythm))
        menusAdapter.addData(HomeMenus(4, "structure", "曲式结构", R.drawable.icon_structure, "structure.json", R.drawable.chapter_structure, R.drawable.container_rhythm))
        menusAdapter.addData(HomeMenus(5, "speed", "力度速度", R.drawable.icon_speed, "speed.json", R.drawable.chapter_speed, R.drawable.container_rhythm))
        menusAdapter.addData(HomeMenus(6, "timbre", "音色", R.drawable.icon_timbre, "timbre.json", R.drawable.chapter_timbre, R.drawable.container_rhythm))

    }

}