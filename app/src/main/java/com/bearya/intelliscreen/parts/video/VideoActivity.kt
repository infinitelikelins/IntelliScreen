package com.bearya.intelliscreen.parts.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.bearya.intelliscreen.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context, videoPath: String) {
            context.startActivity(Intent(context, VideoActivity::class.java).putExtra("videoPath", videoPath))
        }
    }

    private lateinit var bindView: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindView = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(bindView.root)

        bindView.video.setVideoPath(intent.getStringExtra("videoPath"))
        bindView.video.setMediaController(MediaController(this))

        bindView.video.setOnCompletionListener {
            finish()
        }

        bindView.video.requestFocus()
        bindView.video.start()

    }

    override fun onStop() {
        super.onStop()
        bindView.video.stopPlayback()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

}