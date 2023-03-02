package com.bearya.intelliscreen.parts.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.databinding.FragmentHomeBinding

// 首页菜单
class HomeFragment : Fragment() {

    private lateinit var bindView: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val menusAdapter: MenusAdapter by lazy { MenusAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

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

        menusAdapter.setEmptyView(R.layout.empty_list)

        menusAdapter.setOnItemClickListener { _, v, position ->

            val item = menusAdapter.getItem(position)
            val directions = HomeFragmentDirections.actionHomeFragmentToChapterFragment(item.name, item.file, item.subTitleIcon, item.subContainerIcon)
            Navigation.findNavController(v).navigate(directions)

        }

        menusAdapter.setNewInstance(viewModel.list)

    }

}