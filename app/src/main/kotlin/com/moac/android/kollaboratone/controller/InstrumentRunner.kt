package com.moac.android.kollaboratone.controller

import android.view.SurfaceHolder
import com.moac.android.kollaboratone.AnimatorLoop
import com.moac.android.kollaboratone.instrument.Instrument
import com.moac.android.kollaboratone.view.InstrumentRenderer

class InstrumentRunner(val instrument: Instrument,
                       val render: InstrumentRenderer) {

    private var loopThread: AnimatorLoop? = null;

    fun start(holder: SurfaceHolder) {
        loopThread = AnimatorLoop(holder, instrument, render)
        loopThread?.apply {
            setRunning(true)
            start()
        }
    }

    fun stop() {
        var retry = true
        while (retry) {
            try {
                loopThread?.apply {
                    setRunning(false)
                    join()
                }
                retry = false
            } catch (e: InterruptedException) {
                // try again shutting down the loop?
            }
        }
    }

}