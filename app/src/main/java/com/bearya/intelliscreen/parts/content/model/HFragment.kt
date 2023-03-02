package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.PageH
import com.bearya.intelliscreen.databinding.ModelPABinding
import com.bearya.intelliscreen.library.tool.Music
import com.bearya.intelliscreen.library.tool.Storage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


/**
 * 模板8： 背景一张 + 音频一个 （PA）
 */
class HFragment : Fragment() {

    companion object {
        fun newInstance(item: PageH?): HFragment =
            HFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPABinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val item = arguments?.getSerializable("item") as? PageH?

        val backgroundPath = Storage.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.background)

        bindView.audio.requestFocus()

        bindView.audio.setOnClickListener {
            Music.autoAudio(Storage.getUsbDir(requireContext()) + item?.audio) { isPlaying , _ ->
                bindView.audio.setImageResource(if (isPlaying) R.drawable.music_stop else R.drawable.music_play)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Music.stop()
    }

}
