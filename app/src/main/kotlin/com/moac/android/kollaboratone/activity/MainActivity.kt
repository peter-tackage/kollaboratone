package com.moac.android.kollaboratone.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.moac.android.kollaboratone.R
import com.moac.android.kollaboratone.controller.InstrumentRunner
import com.moac.android.kollaboratone.instrument.InstrumentMaker

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {

    private var instrumentRunner: InstrumentRunner? = null
    private var arenaSurfaceView: SurfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arenaSurfaceView = (findViewById(R.id.arena_surfaceView) as SurfaceView)
        arenaSurfaceView?.let {
            it.holder.addCallback(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        arenaSurfaceView = null;
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Log.d("InstrumentController", "InstrumentView surfaceCreated()")
        arenaSurfaceView?.let {
            instrumentRunner = InstrumentMaker(it.width, it.height).createInstrumentRunner()
            instrumentRunner?.start(holder)
        }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Log.d("InstrumentController", "InstrumentView surfaceDestroyed()")
        instrumentRunner?.stop()
    }
}
