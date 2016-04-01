package com.moac.android.kollaboratone.view

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.moac.android.kollaboratone.instrument.Instrument
import com.moac.android.kollaboratone.model.Arena

class InstrumentRenderer(val paint: Paint) {

    fun render(instrument: Instrument, canvas: Canvas) {
        drawBackground(canvas)
        drawBalls(instrument.arena, canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        // Draw the background - the canvas with black
        canvas.drawColor(Color.WHITE);
    }

    private fun drawBalls(arena: Arena, canvas: Canvas) {
        // Draw the balls
        arena.getShapes().forEach {
            //   Log.v("Renderer", "Rendering: %s".format(it))
            paint.color = it.color.toInt()
            canvas.drawCircle(it.xpos, it.ypos, it.radius, this.paint);
            paint.color = Color.WHITE
            paint.textSize = it.radius
            paint.textAlign = Paint.Align.CENTER
            canvas.drawText(it.octaveNote.note.displayName, it.xpos, it.ypos + it.radius.div(4), paint)
        }
    }
}