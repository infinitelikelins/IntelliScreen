package com.bearya.intelliscreen.parts.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.data.event.KeyEvents
import com.bearya.intelliscreen.data.event.PermissionEvent
import com.bearya.intelliscreen.databinding.ActivityMainBinding
import com.bearya.intelliscreen.parts.splash.REQUEST_CODE_EXTERNAL_STORAGE
import com.bearya.intelliscreen.parts.splash.SplashFragment
import es.dmoral.toasty.Toasty
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {

    private lateinit var bindView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindView.root)
    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.fragment_container).navigateUp()

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        EventBus.getDefault().post(KeyEvents(keyCode))
        return super.onKeyDown(keyCode, event)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == REQUEST_CODE_EXTERNAL_STORAGE) {
            EventBus.getDefault().post(PermissionEvent(requestCode, Manifest.permission.READ_EXTERNAL_STORAGE, grantResults[0]))
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}