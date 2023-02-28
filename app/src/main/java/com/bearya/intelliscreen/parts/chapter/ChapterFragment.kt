package com.bearya.intelliscreen.parts.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.MenuChapter
import com.bearya.intelliscreen.databinding.FragmentChapterBinding
import com.bearya.intelliscreen.library.tool.StorageTool
import com.bumptech.glide.Glide

class ChapterFragment : Fragment() {

    private val chapterAdapter by lazy { ChapterAdapter() }
    private val args by navArgs<ChapterFragmentArgs>()
    private val viewModel by viewModels<ChapterViewModel>()
    private lateinit var bindView: FragmentChapterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(args.file)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentChapterBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Glide.with(requireContext()).load(StorageTool.getUsbDir(requireContext()) + args.subTitleIcon).into(bindView.chapterIcon)
        Glide.with(requireContext()).load(StorageTool.getUsbDir(requireContext()) + args.subContainerIcon).into(bindView.background)

        bindView.chapters.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bindView.chapters.itemAnimator = DefaultItemAnimator()
        bindView.chapters.adapter = chapterAdapter

        chapterAdapter.setOnItemClickListener { _, v, position ->
            Navigation.findNavController(v).navigate(ChapterFragmentDirections.actionChapterFragmentToPagerFragment(viewModel.list[position].itemFile))
        }

        chapterAdapter.setNewInstance(viewModel.list)

    }

}