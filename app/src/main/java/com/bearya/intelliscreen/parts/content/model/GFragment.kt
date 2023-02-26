package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageG
import com.bearya.intelliscreen.databinding.ModelPVVVVBinding

/**
 * 模板7： 背景一张 + 视频四个 (PVVVV)
 */
class GFragment : Fragment() {

    companion object {
        fun newInstance(item: PageG?): GFragment =
            GFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVVVVBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVVVVBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindView.videoLeftUp.requestFocus()

        bindView.videoLeftUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeftUp.setBorderWidth(width)
        }
        bindView.videoLeftDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeftDown.setBorderWidth(width)
        }
        bindView.videoRightUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightUp.setBorderWidth(width)
        }
        bindView.videoRightDown.onFocusChangeListener= View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightDown.setBorderWidth(width)
        }

        bindView.videoLeftUp.setOnClickListener {  }
        bindView.videoLeftDown.setOnClickListener {  }
        bindView.videoRightUp.setOnClickListener {  }
        bindView.videoRightDown.setOnClickListener {  }

    }

}
