package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageI
import com.bearya.intelliscreen.databinding.ModelPAAABinding
import com.bearya.intelliscreen.library.tool.StorageTool
import com.bumptech.glide.Glide


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

        val backgroundPath = StorageTool.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .into(bindView.background)

        val audioACover = StorageTool.getUsbDir(requireContext()) + item?.audioAIcon

        Glide.with(view)
            .load(audioACover)
            .into(bindView.audioLeft)

        val audioBCover = StorageTool.getUsbDir(requireContext()) + item?.audioBIcon

        Glide.with(view)
            .load(audioBCover)
            .into(bindView.audioUp)

        val audioCCover = StorageTool.getUsbDir(requireContext()) + item?.audioCIcon

        Glide.with(view)
            .load(audioCCover)
            .into(bindView.audioRight)

        bindView.audioLeft.requestFocus()

        bindView.audioUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            bindView.audioUp.setBorderWidth(if (hasFocus) 3 else 0)
            bindView.playUp.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
        }
        bindView.audioLeft.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            bindView.audioLeft.setBorderWidth(if (hasFocus) 3 else 0)
            bindView.playLeft.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
        }
        bindView.audioRight.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            bindView.audioRight.setBorderWidth(if (hasFocus) 3 else 0)
            bindView.playRight.visibility = if (hasFocus) View.VISIBLE else View.INVISIBLE
        }

        bindView.audioUp.setOnClickListener {  }
        bindView.audioRight.setOnClickListener {  }
        bindView.audioLeft.setOnClickListener {  }

    }

}
