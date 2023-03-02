package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.bean.PageI
import com.bearya.intelliscreen.databinding.ModelPAAABinding
import com.bearya.intelliscreen.library.tool.Music
import com.bearya.intelliscreen.library.tool.Storage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * 模板9： 背景一张 + 音频三个 （PAAA）
 */
class IFragment : Fragment() {

    companion object {
        fun newInstance(item: PageI?): IFragment =
            IFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPAAABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPAAABinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val item = arguments?.getSerializable("item") as? PageI?

        val backgroundPath = Storage.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.background)

        val audioACover = Storage.getUsbDir(requireContext()) + item?.audioAIcon

        Glide.with(view)
            .load(audioACover)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.audioLeft)

        val audioBCover = Storage.getUsbDir(requireContext()) + item?.audioBIcon

        Glide.with(view)
            .load(audioBCover)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.audioUp)

        val audioCCover = Storage.getUsbDir(requireContext()) + item?.audioCIcon

        Glide.with(view)
            .load(audioCCover)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.audioRight)

        bindView.audioLeft.requestFocus()

        bindView.audioUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            bindView.audioUp.setBorderWidth(if (hasFocus) 3 else 0)
            bindView.playUp.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
            if (hasFocus) Music.stop() else bindView.playUp.setImageResource(R.drawable.music_play)
        }
        bindView.audioLeft.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            bindView.audioLeft.setBorderWidth(if (hasFocus) 3 else 0)
            bindView.playLeft.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
            if (hasFocus) Music.stop() else bindView.playLeft.setImageResource(R.drawable.music_play)
        }
        bindView.audioRight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            bindView.audioRight.setBorderWidth(if (hasFocus) 3 else 0)
            bindView.playRight.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
            if (hasFocus) Music.stop() else bindView.playRight.setImageResource(R.drawable.music_play)
        }

        bindView.audioUp.setOnClickListener {
            Music.autoAudio(Storage.getUsbDir(requireContext()) + item?.audioB) { isPlaying , message ->
                bindView.playUp.setImageResource(if (isPlaying) R.drawable.music_stop else R.drawable.music_play)
            }
        }

        bindView.audioRight.setOnClickListener {
            Music.autoAudio(Storage.getUsbDir(requireContext()) + item?.audioC) { isPlaying , message ->
                bindView.playRight.setImageResource(if (isPlaying) R.drawable.music_stop else R.drawable.music_play)
            }
        }

        bindView.audioLeft.setOnClickListener {
            Music.autoAudio(Storage.getUsbDir(requireContext()) + item?.audioA) { isPlaying , message ->
                bindView.playLeft.setImageResource(if (isPlaying) R.drawable.music_stop else R.drawable.music_play)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Music.stop()
    }

}
