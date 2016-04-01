package com.moac.android.kollaboratone.controller

/**
 *
 * @author Peter Tackage
 * @since 01/04/16
 */
class InteractionMonitor { //} : Touch {
    //    override fun onTouchEvent(event: MotionEvent): Boolean {
    //        Log.d(TAG, "InstrumentView: onTouchEvent() called")
    //        for (shape in mArena.getShapes()) {
    //            if (event.action == MotionEvent.ACTION_DOWN) {
    //                // delegating event handling to the obj
    //                val handled = shape.handleActionDown(event.x.toInt(), event.y.toInt())
    //                if (handled) {
    //                    mInstrument.touchDown(event, shape)
    //                    return true
    //                    // TODO Define some sort of ordering that is clear!.
    //                }
    //            } else if (event.action == MotionEvent.ACTION_MOVE) {
    //                if (shape.isTouched()) {
    //                    // the obj was picked up and is being dragged
    //
    //                    // TODO Ignore small movements ie taps
    //                    // TODO Need some sort of layering so we can pick up
    //                    // the "top" one and move only it when they overlap.
    //                    // TODO lots of behaviour possibilities here
    //                    //if (!obj.isInside(event.getX(), event.getY()))
    //                    //{
    //                    shape.setXPos(event.x.toInt())
    //                    shape.setYPos(event.y.toInt())
    //                    mInstrument.touchMove(event, shape)
    //                    // TODO Interesting, the other "incircle" one still stops. cos it has a "mIsTouched" register thru onDown
    //                    return true
    //                    //}
    //                }
    //            } else if (event.action == MotionEvent.ACTION_UP) {
    //                // touch was released
    //                if (shape.isTouched()) {
    //                    shape.setIsTouched(false)
    //                }
    //                mInstrument.touchUp(event, shape)
    //                return true
    //            }
    //        }
    //        return false
    //    }
}