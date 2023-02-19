package com.bearya.intelliscreen.parts.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bearya.intelliscreen.databinding.FragmentHomeBinding

// 首页菜单
class HomeFragment : Fragment() {

    private lateinit var bindView: FragmentHomeBinding

    private val menusAdapter : MenusAdapter by lazy { MenusAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentHomeBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindView.menus.layoutManager = GridLayoutManager(requireContext() , 3 , GridLayoutManager.VERTICAL , false)
        bindView.menus.itemAnimator = DefaultItemAnimator()
        bindView.menus.adapter = menusAdapter

    }

}