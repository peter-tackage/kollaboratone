package com.moac.android.kollaboratone.instrument

import com.moac.android.kollaboratone.controller.InstrumentInteractor
import com.moac.android.kollaboratone.controller.InstrumentRunner
import com.moac.android.kollaboratone.controller.NoteGenerator
import com.moac.android.kollaboratone.model.*
import com.moac.android.kollaboratone.player.PlayerModule
import com.moac.android.kollaboratone.view.RendererModule

class InstrumentModule(val width: Int, val height: Int, val playerModule: PlayerModule, val rendererModule: RendererModule) {

    val instrument by lazy { Instrument(provideArena(), provideOverlapDetector(), playerModule.createPlayer()) }

    fun provideInstrumentRunner() = InstrumentRunner(instrument, rendererModule.provideRenderer())

    fun provideInteractionMonitor() = InstrumentInteractor(instrument)

    private fun provideArena(): Arena {
        val arena = Arena(width, height)
        provideInitialBalls().forEach { arena.addShape(it) }
        return arena
    }

    private fun provideOverlapDetector() = NoteGenerator()

    private fun provideInitialBalls(): Collection<Ball> {

        val widthSegment = width.div(7)
        val halfHeight = height.div(2).toFloat()

        val b1 = Ball("A-1", OctaveNote(KEY.get(0), -1), COLORS.get(0), 150F, widthSegment.toFloat(), halfHeight, Velocity(0, localSpeed(5), Direction.NONE, Direction.UP), false)
        val b2 = Ball("C", OctaveNote(KEY.get(2), 0), COLORS.get(2), 100F, (widthSegment * 2).toFloat(), halfHeight, Velocity(0, localSpeed(10), Direction.NONE, Direction.DOWN), false)
        val b2plus1 = Ball("C+1", OctaveNote(KEY.get(2), 1), COLORS.get(8), 80F, (widthSegment * 3).toFloat(), halfHeight, Velocity(0, localSpeed(12), Direction.NONE, Direction.DOWN), false)
        val b3 = Ball("E", OctaveNote(KEY.get(4), 0), COLORS.get(4), 100F, (widthSegment * 4).toFloat(), halfHeight, Velocity(0, localSpeed(15), Direction.NONE, Direction.UP), false)
        val b4 = Ball("G", OctaveNote(KEY.get(6), 0), COLORS.get(6), 100F, (widthSegment * 5).toFloat(), halfHeight, Velocity(0, localSpeed(20), Direction.NONE, Direction.DOWN), false)
        return listOf(b1, b2, b2plus1, b3, b4)
    }

    private val WORLD_SPEED: Float = 4.0F
    private val KEY: List<Note> = listOf(Note.A, Note.B, Note.C, Note.D, Note.E, Note.F, Note.G)
    private val COLORS: List<Long> = listOf(0xfff44336, 0xffe91e63, 0xff09c27b0, 0xfff673ab7,
            0xff3f51b5, 0xff2196f3, 0xff03a9f4, 0xff4caf50, 0xffffeb3b)

    private fun localSpeed(speed: Int): Int {
        return speed.times(WORLD_SPEED).toInt()
    }
}

