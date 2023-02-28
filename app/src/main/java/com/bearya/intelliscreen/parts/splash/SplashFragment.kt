package com.bearya.intelliscreen.parts.splash

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bearya.intelliscreen.BuildConfig
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.event.PermissionEvent
import com.bearya.intelliscreen.databinding.FragmentSplashBinding
import com.bearya.intelliscreen.library.ext.VERIFY_RESULT
import com.tencent.mmkv.MMKV
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

// 权限请求CODE：外部存取设备
const val REQUEST_CODE_EXTERNAL_STORAGE = 0x10

// 启动
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        EventBus.getDefault().register(this)
        return FragmentSplashBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_EXTERNAL_STORAGE)
        } else {
            launch()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
        lifecycleScope.cancel()
    }

    private fun launch() {
        lifecycleScope.launchWhenResumed {
            // 延迟3s
            delay(3000)

            // 跳转到首页菜单
            if (MMKV.defaultMMKV().decodeBool(VERIFY_RESULT, false)) {
                Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else if (BuildConfig.DEBUG) {
                Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(SplashFragmentDirections.actionSplashFragmentToCodeFragment())
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onPermission(event: PermissionEvent) {
        if (event.permission == Manifest.permission.READ_EXTERNAL_STORAGE) {
            if (event.grantResults == PackageManager.PERMISSION_DENIED) {
                Toasty.error(requireContext(), "你拒绝使用外部存储申请,将无法读取课件", Toasty.LENGTH_LONG).show()
            } else {
                launch()
            }
        }
    }

}