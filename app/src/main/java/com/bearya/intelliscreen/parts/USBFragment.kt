package com.bearya.intelliscreen.parts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bearya.intelliscreen.databinding.FragmentUsbBinding
import com.bearya.intelliscreen.library.tool.StorageTool
import java.io.File

class USBFragment : Fragment() {

    private lateinit var bindView: FragmentUsbBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bindView = FragmentUsbBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView.root.requestFocus()

        init()

    }

    fun init() {

        try {
            bindView.message.append("初始化开始了")

            val usbDir = StorageTool.getUsbDir(requireContext())
            bindView.message.append("usbStoragePath = $usbDir\n")

            File(usbDir).walk()
                .maxDepth(4)
                .forEach {
                    bindView.message.append("file dir = ${it.absolutePath}\n")
                }

        } catch (ex: Exception) {
            bindView.message.append("异常")
        }

    }

}