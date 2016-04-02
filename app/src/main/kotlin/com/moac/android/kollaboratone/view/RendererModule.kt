package com.moac.android.kollaboratone.view

import android.content.Context

class RendererModule(val context: Context) {

    fun provideRenderer() = InstrumentRenderer(context)

}