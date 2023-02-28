package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageE
import com.bearya.intelliscreen.databinding.ModelPVVVLRRBinding
import com.bearya.intelliscreen.library.tool.StorageTool
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import es.dmoral.toasty.Toasty


/**
 * 模板5： 背景一张 + 视频三个--左下,右上,右下 (PVVVLRR)
 */
class EFragment : Fragment() {

    companion object {
        fun newInstance(item: PageE?): EFragment =
            EFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPVVVLRRBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPVVVLRRBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val item = arguments?.getSerializable("item") as? PageE?

        val backgroundPath = StorageTool.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(backgroundPath)
            .into(bindView.background)


        val videoAPath = StorageTool.getUsbDir(requireContext()) + item?.videoA

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoAPath)
            .into(bindView.videoLeftDown)

        val videoBPath = StorageTool.getUsbDir(requireContext()) + item?.videoB

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoBPath)
            .into(bindView.videoRightUp)

        val videoCPath = StorageTool.getUsbDir(requireContext()) + item?.videoC

        Glide.with(view)
            .setDefaultRequestOptions(
                RequestOptions()
                    .frame(10)
                    .skipMemoryCache(true)
                    .fitCenter())
            .load(videoCPath)
            .into(bindView.videoRightDown)

        bindView.videoLeftDown.requestFocus()

        bindView.videoLeftDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoLeftDown.setBorderWidth(width)
        }
        bindView.videoLeftDown.setOnClickListener {  Toasty.success(requireContext() , "up" , 2000).show() }

        bindView.videoRightDown.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightDown.setBorderWidth(width)
        }
        bindView.videoRightDown.setOnClickListener {  Toasty.success(requireContext() , "down" , 2000).show() }

        bindView.videoRightUp.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            val width = if (hasFocus) 3 else 0
            bindView.videoRightUp.setBorderWidth(width)
        }
        bindView.videoRightUp.setOnClickListener {  Toasty.success(requireContext() , "down" , 2000).show() }

    }

}