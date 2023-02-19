package com.bearya.intelliscreen.parts.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.databinding.FragmentSplashBinding
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 启动
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSplashBinding.inflate(inflater, container, false).root
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            // 延迟3s
            delay(3000)
            // 跳转到首页菜单
            Navigation.findNavController(requireActivity(), R.id.fragment_container).navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycleScope.cancel()
    }

}