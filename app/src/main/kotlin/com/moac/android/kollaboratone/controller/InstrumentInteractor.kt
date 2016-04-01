package com.moac.android.kollaboratone.controller

import android.view.MotionEvent
import android.view.View
import com.moac.android.kollaboratone.instrument.Instrument
import com.moac.android.kollaboratone.model.Ball

class InstrumentInteractor(val instrument: Instrument) : View.OnTouchListener {

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        event?.let {
            for (shape in instrument.shapes()) {
                if (event.action == MotionEvent.ACTION_DOWN) {
                    // delegating event handling to the obj
                    if (event.isInside(shape)) {
                        this.instrument.holdShape(shape)
                        return true
                        // TODO Define some sort of ordering that is clear!.
                    }
                } else if (event.action == MotionEvent.ACTION_MOVE) {
                    if (shape.isHeld) {
                        // TODO Ignore small movements ie taps
                        // TODO Need some sort of layering so we can pick up
                        // the "top" one and move only it when they overlap.
                        // TODO lots of behaviour possibilities here
                        this.instrument.moveShape(shape, event.x, event.y)
                        return true
                    }
                } else if (event.action == MotionEvent.ACTION_UP) {
                    // touch was released
                    if (shape.isHeld) {
                        this.instrument.releaseShape(shape)
                        return true
                    }
                }
            }
        }
        return false;
    }

    private fun MotionEvent.isInside(ball: Ball): Boolean {
        return inCircle(x.toDouble(), y.toDouble(), ball.xpos.toDouble(), ball.ypos.toDouble(), ball.radius.toDouble())
    }

    private fun inCircle(x: Double,
                         y: Double,
                         circleCenterX: Double,
                         circleCenterY: Double,
                         circleRadius: Double): Boolean {
        val dx: Double = Math.pow(x - circleCenterX, 2.toDouble())
        val dy: Double = Math.pow(y - circleCenterY, 2.toDouble())

        if ((dx + dy) < Math.pow(circleRadius, 2.toDouble())) {
            return true;
        } else {
            return false;
        }
    }
}