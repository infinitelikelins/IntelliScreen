package com.bearya.intelliscreen.parts.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import com.bearya.intelliscreen.R
import com.bearya.intelliscreen.databinding.ActivityMainBinding
import es.dmoral.toasty.Toasty

// 权限请求CODE：外部存取设备
const val REQUEST_CODE_EXTERNAL_STORAGE = 0x10

class MainActivity : AppCompatActivity() {

    private lateinit var bindView: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindView.root)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this , arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) , REQUEST_CODE_EXTERNAL_STORAGE)
        }

    }

    override fun onSupportNavigateUp(): Boolean = Navigation.findNavController(this, R.id.fragment_container).navigateUp()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissions.forEachIndexed { index, permission ->
            when(permission) {
                Manifest.permission.READ_EXTERNAL_STORAGE ->
                    if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                        Toasty.error(this, "您拒绝使用存储器,将无法正常使用课件功能", Toasty.LENGTH_LONG).show()
                    }
            }
        }
    }

}