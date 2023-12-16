package com.moac.android.kollaboratone;

import com.moac.android.kollaboratone.instrument.Instrument;
import com.moac.android.kollaboratone.view.InstrumentRenderer;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.SurfaceHolder;

public class AnimatorLoop extends Thread {

    private static String TAG = AnimatorLoop.class.getSimpleName();

    private static final int MAX_FPS = 50;                  // desired fps
    private static final int MAX_FRAME_SKIPS = 5;
    // maximum number of frames to be skipped
    private static final int FRAME_PERIOD = 1000 / MAX_FPS; // the frame period

    private volatile boolean mIsRunning = true;

    private final SurfaceHolder surfaceHolder;
    private final Instrument instrument;
    private final InstrumentRenderer renderer;

    public AnimatorLoop(@NonNull final SurfaceHolder surfaceHolder,
                        @NonNull final Instrument instrument,
                        @NonNull final InstrumentRenderer renderer) {
        Log.i(TAG, "AnimateLoop created");
        this.surfaceHolder = surfaceHolder;
        this.instrument = instrument;
        this.renderer = renderer;
    }

    @Override
    public void run() {
        Canvas canvas;
        Log.d(TAG, "Starting animation loop");

        long beginTime;          // the time when the cycle begun
        long timeDiff;      // the time it took for the cycle to execute
        int sleepTime;      // ms to sleep (<0 if we're behind)
        int framesSkipped;      // number of frames being skipped

        while (mIsRunning) {
            canvas = null;
            // try locking the canvas for exclusive pixel editing
            // in the surface
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    beginTime = System.currentTimeMillis();
                    framesSkipped = 0;  // resetting the frames skipped

                    // update instrument state
                    instrument.update();

                    // render state to the screen
                    // draws the canvas on the panel
                    renderer.render(instrument, canvas);

                    // calculate how long did the cycle take
                    timeDiff = System.currentTimeMillis() - beginTime;
                    // calculate sleep time
                    sleepTime = (int) (FRAME_PERIOD - timeDiff);

                    if (sleepTime > 0) {
                        // if sleepTime > 0 we're OK
                        try {
                            // send the thread to sleep for a short period
                            // very useful for battery saving
                            sleep(sleepTime);
                        } catch (InterruptedException e) {
                        }
                    }

                    while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {

                        // we need to catch up
                        // update without rendering
                        instrument.update();
                        // add frame period to check if in next frame
                        sleepTime += FRAME_PERIOD;
                        framesSkipped++;
                    }
                }
            } finally {
                // in case of an exception the surface is not left in
                // an inconsistent state
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }   // end finally
        }
    }

    public void safeStop() {
        Log.d(TAG, "safeStop() called");
        mIsRunning = false;
        interrupt();
    }

    public void setRunning(boolean running) {
        mIsRunning = running;
    }

}
