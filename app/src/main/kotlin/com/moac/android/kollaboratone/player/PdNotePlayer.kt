package com.moac.android.kollaboratone.player

import android.content.Context
import com.moac.android.kollaboratone.R
import com.moac.android.kollaboratone.model.OctaveNote
import org.puredata.android.io.AudioParameters
import org.puredata.android.io.PdAudio
import org.puredata.core.PdBase
import org.puredata.core.utils.IoUtils
import java.io.File
import kotlin.math.max

class PdNotePlayer(private val context: Context) : NotePlayer {
    private companion object {
        private const val MIN_SAMPLE_RATE = 44100
    }

    override fun init() {
        initPd()
    }

    override fun startAudio() {
        PdAudio.startAudio(context)
    }

    override fun stopAudio() {
        PdAudio.stopAudio()
    }

    override fun dispose() {
        cleanup()
    }

    override fun playNotes(note1: OctaveNote, note2: OctaveNote) {
        val note1Hz: Float = shiftForOctave(note1)
        val note2Hz: Float = shiftForOctave(note2)
        PdBase.sendFloat("freq1", note1Hz)
        PdBase.sendFloat("freq2", note2Hz)
        //   PdBase.sendFloat("onOff", 1f)
    }

    private fun shiftForOctave(note: OctaveNote): Float {
        return if (note.octaveMultipler > 0) {
            note.note.frequency.times(2.times(note.octaveMultipler))
        } else if (note.octaveMultipler < 0) {
            note.note.frequency.div(2.times(note.octaveMultipler))
        } else {
            note.note.frequency
        }
    }

    override fun silence() {
        // Better than turning on off
        PdBase.sendFloat("freq1", 0f)
        PdBase.sendFloat("freq2", 0f)
    }

    private fun initPd() {
        AudioParameters.init(context)
        val srate = max(MIN_SAMPLE_RATE, AudioParameters.suggestSampleRate())
        PdAudio.initAudio(srate, 0, 2, 64, true)

        val dir: File = context.filesDir
        val patchFile = File(dir, "very_simple_sine.pd")
        IoUtils.extractResource(context.resources.openRawResource(R.raw.very_simple_sine),
                patchFile.name, context.filesDir)
        //context.resources.ra
        //IoUtils.extractZipResource(context.resources.openRawResource(R.raw.patch), dir, true);
        PdBase.openPatch(patchFile)
    }

    private fun cleanup() {
        // make sure to release all resources
        PdAudio.release()
        PdBase.release()
    }

}