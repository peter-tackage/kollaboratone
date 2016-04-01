package com.moac.android.kollaboratone.instrument

import android.graphics.Paint
import com.moac.android.kollaboratone.controller.InstrumentInteractor
import com.moac.android.kollaboratone.controller.InstrumentRunner
import com.moac.android.kollaboratone.model.*
import com.moac.android.kollaboratone.player.NoOpPlayer
import com.moac.android.kollaboratone.player.NotePlayer
import com.moac.android.kollaboratone.view.InstrumentRenderer

class InstrumentMaker(val width: Int, val height: Int) {

    private var instrument: Instrument? = null;

    fun createInstrumentRunner() = InstrumentRunner(provideInstrument(), createInstrumentRenderer())

    fun createIteractionMonitor() = InstrumentInteractor(provideInstrument())

    private fun createInstrumentRenderer() = InstrumentRenderer(createPaint())

    private fun provideInstrument(): Instrument {
        synchronized(this, {
            if (this.instrument == null) {
                this.instrument = Instrument(createArena(), createNotePlayer())
            }
        })
        return this.instrument!! // bah.
    }

    private fun createArena(): Arena {
        val arena = Arena(width, height)
        createInitialBalls().forEach { arena.addShape(it) }
        return arena
    }

    private fun createNotePlayer(): NotePlayer = NoOpPlayer()

    private fun createPaint() = Paint()

    private fun createInitialBalls(): Collection<Ball> {

        val widthSegment = width.div(5)
        val halfHeight = height.div(2).toFloat()

        val b1: Ball = Ball("1", OctaveNote(KEY.get(0), 0), COLORS.get(0), 150F, widthSegment.toFloat(), halfHeight, Velocity(0, 5, Direction.NONE, Direction.UP), false)
        val b2: Ball = Ball("2", OctaveNote(KEY.get(2), 0), COLORS.get(2), 100F, (widthSegment * 2).toFloat(), halfHeight, Velocity(0, 10, Direction.NONE, Direction.DOWN), false)
        val b3: Ball = Ball("3", OctaveNote(KEY.get(4), 0), COLORS.get(4), 100F, (widthSegment * 3).toFloat(), halfHeight, Velocity(0, 15, Direction.NONE, Direction.UP), false)
        val b4: Ball = Ball("4", OctaveNote(KEY.get(6), 0), COLORS.get(6), 100F, (widthSegment * 4).toFloat(), halfHeight, Velocity(0, 20, Direction.NONE, Direction.DOWN), false)
        return listOf(b1, b2, b3, b4)
    }

    private val KEY: List<Note> = listOf(Note.A, Note.B, Note.C, Note.D, Note.E, Note.F, Note.G)
    private val COLORS: List<Long> = listOf(0xfff44336, 0xffe91e63, 0xff09c27b0, 0xfff673ab7,
            0xff3f51b5, 0xff2196f3, 0xff03a9f4)
}

