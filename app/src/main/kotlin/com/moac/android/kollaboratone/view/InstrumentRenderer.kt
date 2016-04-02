package com.moac.android.kollaboratone.view

import android.content.Context
import android.graphics.*
import com.moac.android.kollaboratone.R
import com.moac.android.kollaboratone.instrument.Instrument
import com.moac.android.kollaboratone.model.Ball

class InstrumentRenderer(val context: Context) {

    private val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.space)
    private val shadowColor = Color.parseColor("#33000000")

    fun render(instrument: Instrument, canvas: Canvas) {
        drawBackground(canvas)
        drawBalls(instrument.shapes(), canvas)
    }

    private fun drawBackground(canvas: Canvas) {
        // Draw the background - the canvas with black
        //  Log.v("Renderer", "### Starting to draw bitmap")
        // val drawable: Drawable = context.resources.getDrawable(R.drawable.space, context.theme)
        //   drawable.setBounds(0, 0, canvas.width, canvas.height);
        //  canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, null, Rect(0, 0, canvas.width, canvas.height), Paint())
        //   Log.v("Renderer", "### Finished to draw bitmap")

    }

    private fun drawBalls(shapes: Collection<Ball>, canvas: Canvas) {
        // Draw the balls
        val paint: Paint = Paint()
        shapes.forEach {
            paint.reset()
            paint.color = shadowColor
            canvas.drawCircle(it.xpos + 7, it.ypos + 10, it.radius, paint)
            paint.color = it.color.toInt()
            canvas.drawCircle(it.xpos, it.ypos, it.radius, paint)

            paint.reset()
            paint.color = Color.WHITE
            paint.textSize = it.radius
            paint.textAlign = Paint.Align.CENTER

            var displayName = it.octaveNote.note.displayName
            if (it.octaveNote.octaveMultipler != 0) {
                displayName += it.octaveNote.octaveMultipler
            }
            canvas.drawText(displayName, it.xpos, it.ypos + it.radius.div(4), paint)
        }
    }
}