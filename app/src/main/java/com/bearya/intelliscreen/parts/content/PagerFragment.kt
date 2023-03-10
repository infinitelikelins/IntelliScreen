package com.bearya.intelliscreen.parts.content

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.event.KeyEvents
import com.bearya.intelliscreen.databinding.FragmentPagerBinding
import com.bearya.intelliscreen.library.tool.Music
import com.orhanobut.logger.Logger
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

// 第三层文件课
class PagerFragment : Fragment() {

    private lateinit var bindView: FragmentPagerBinding
    private val args by navArgs<PagerFragmentArgs>()

    private val viewModel by viewModels<PagerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init(args.file)
        Music.init(requireContext())
        EventBus.getDefault().register(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentPagerBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.currentFragment.observe(viewLifecycleOwner) { fragment ->
            if (fragment != null) {
                childFragmentManager.commit {
                    replace(R.id.page_container, fragment)
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onKeyEvents(event: KeyEvents) {
        when (event.keyCode) {
            KeyEvent.KEYCODE_DPAD_RIGHT -> viewModel.next()
            KeyEvent.KEYCODE_DPAD_LEFT -> viewModel.up()
        }
    }

    override fun onDestroy() {
        Music.destroy()
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

}