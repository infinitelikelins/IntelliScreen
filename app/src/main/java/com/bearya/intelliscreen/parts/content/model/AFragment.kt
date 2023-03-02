package com.bearya.intelliscreen.parts.content.model

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.data.bean.PageA
import com.bearya.intelliscreen.databinding.ModelPBinding
import com.bearya.intelliscreen.library.tool.Storage
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * 模板1 ： 纯图片 一张 (P)
 */
class AFragment : Fragment() {

    companion object {
        fun newInstance(item: PageA?): AFragment =
            AFragment().apply { arguments = bundleOf("item" to item) }
    }

    private lateinit var bindView: ModelPBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = ModelPBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = arguments?.getSerializable("item") as? PageA?

        val path = Storage.getUsbDir(requireContext()) + item?.background

        Glide.with(view)
            .load(path)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(bindView.picture)
    }

}