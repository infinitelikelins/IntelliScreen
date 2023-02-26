package com.bearya.intelliscreen.parts.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.MenuChapter
import com.bearya.intelliscreen.databinding.FragmentChapterBinding

class ChapterFragment : Fragment() {

    private val chapterAdapter by lazy { ChapterAdapter() }
    private val args by navArgs<ChapterFragmentArgs>()

    private lateinit var bindView: FragmentChapterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentChapterBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindView.background.setImageResource(args.subContainer)
        bindView.chapterIcon.setImageResource(args.subTitle)

        bindView.chapters.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        bindView.chapters.itemAnimator = DefaultItemAnimator()
        bindView.chapters.adapter = chapterAdapter

        chapterAdapter.addData(MenuChapter(1, R.drawable.theme_acceleration, ""))
        chapterAdapter.addData(MenuChapter(2, R.drawable.theme_farm, ""))
        chapterAdapter.addData(MenuChapter(3, R.drawable.theme_lion, ""))
        chapterAdapter.addData(MenuChapter(4, R.drawable.theme_monkey, ""))
        chapterAdapter.addData(MenuChapter(5, R.drawable.theme_radetzky, ""))
        chapterAdapter.addData(MenuChapter(6, R.drawable.theme_radish, ""))

        chapterAdapter.setOnItemClickListener { adapter, v, position ->
            Navigation.findNavController(v).navigate(ChapterFragmentDirections.actionChapterFragmentToPagerFragment("item File"))
        }

    }

}