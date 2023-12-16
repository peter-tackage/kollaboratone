package com.moac.android.kollaboratone.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.moac.android.kollaboratone.R
import com.moac.android.kollaboratone.controller.InstrumentRunner
import com.moac.android.kollaboratone.instrument.InstrumentModule
import com.moac.android.kollaboratone.player.NotePlayer
import com.moac.android.kollaboratone.player.PlayerModule
import com.moac.android.kollaboratone.view.RendererModule

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private var player: NotePlayer? = null;
    private var instrumentRunner: InstrumentRunner? = null
    private var arenaSurfaceView: SurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = PlayerModule(this).createPlayer()
        player?.init()

        arenaSurfaceView = (findViewById(R.id.arena_surfaceView) as SurfaceView)
        arenaSurfaceView?.holder?.addCallback(this)
    }

    override fun onResume() {
        super.onResume()
        player?.startAudio()
    }

    override fun onPause() {
        player?.stopAudio()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        arenaSurfaceView = null;
        player?.dispose()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        // ignore
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.d("InstrumentController", "InstrumentView surfaceCreated()")
        arenaSurfaceView?.let {
            InstrumentModule(it.width, it.height, PlayerModule(this), RendererModule(this)).apply {
                instrumentRunner = provideInstrumentRunner()
                arenaSurfaceView?.setOnTouchListener(provideInteractionMonitor())
                instrumentRunner?.start(holder)
            }
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d("InstrumentController", "InstrumentView surfaceDestroyed()")
        instrumentRunner?.stop()
    }
}
