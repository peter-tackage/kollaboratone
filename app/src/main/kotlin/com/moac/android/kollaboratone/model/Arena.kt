package com.moac.android.kollaboratone.model

class Arena(val xMax: Int,
            val yMax: Int,
            private val balls: MutableMap<String, Ball> = mutableMapOf()) {
    //   private List<OverlapListener> mOverlapListeners = new ArrayList<OverlapListener>();

    fun addShape(ball: Ball) {
        balls.put(ball.id, ball)
    }

    fun getShapes(): Collection<Ball> {
        return balls.values;
    }

    fun update() {
        synchronized(balls, {
            balls.values.map(updateBall())
                    .forEach { balls.put(it.id, it) }
        })
    }

    // 04-01 16:35:53.156 829-844/com.moac.android.kollaboratone V/Renderer: Rendering: Ball(id=1, octaveNote=OctaveNote(note=A, octaveDelta=0), color=4294198070, radius=150.0, xpos=358.0, ypos=124458.0, velocity=Velocity(xSpeed=0, ySpeed=50, xDirection=NONE, yDirection=UP), isTouched=false)


    private fun updateBall(): (Ball) -> Ball {
        return { old ->
            // TODO Potentially too many allocations here.
            var ball = old

            // check collision with right wall if heading right
            if (ball.velocity.xDirection == Direction.RIGHT
                    && ball.xpos + ball.radius >= xMax) {
                ball = ball.toggleXDirection()
            }
            // check collision with left wall if heading left
            if (ball.velocity.xDirection == Direction.LEFT
                    && ball.xpos - ball.radius <= 0) {
                ball = ball.toggleXDirection()
            }
            // check collision with bottom wall if heading down
            if (ball.velocity.yDirection == Direction.DOWN
                    && ball.ypos + ball.radius >= yMax) {
                ball = ball.toggleYDirection()
            }
            // check collision with top wall if heading up
            if (ball.velocity.yDirection == Direction.UP
                    && ball.ypos - ball.radius <= 0) {
                ball = ball.toggleYDirection()
            }
            // Update position of the ball
            ball.move()

            // TODO Not sure this actually updates
        }
    }

    private fun Ball.toggleXDirection(): Ball {
        return this.copy(this.velocity.toggleXDirection())
    }

    private fun Ball.toggleYDirection(): Ball {
        return this.copy(this.velocity.toggleYDirection())
    }

    private fun Velocity.toggleXDirection(): Velocity {
        return this.copy(this.xDirection.toggle(), this.yDirection)
    }

    private fun Velocity.toggleYDirection(): Velocity {
        return this.copy(this.xDirection, this.yDirection.toggle())
    }

    private fun Direction.toggle(): Direction {
        return when (this) {
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
            Direction.DOWN -> Direction.UP
            Direction.UP -> Direction.DOWN
            Direction.NONE -> Direction.NONE
        }
    }

    private fun Ball.move(): Ball {
        if (this.isTouched) {
            return this
        }
        return this.copy(this.xpos + this.velocity.xSpeed * this.velocity.xDirection.indicator,
                this.ypos + this.velocity.ySpeed * this.velocity.yDirection.indicator)
    }


    //    // Iterate through the shapes
    //    // TODO This currently will assume that all shapes are circles/balls ... it's easier!
    //    List<Overlap> overlaps = new ArrayList<Overlap>();
    //
    //    SIDE_A:
    //    for (AbstractShape s1 : mShapes)
    //    {
    //        Ball b1 = (Ball) s1;
    //
    //        SIDE_B:
    //        for (AbstractShape s2 : mShapes) {
    //
    //        // Now cast them to circles
    //        Ball b2 = (Ball) s2;
    //
    //        // Don't compare to self
    //        if (b1 == b2) continue SIDE_B;
    //
    //        // Check if a collision has already been reported from the other side.
    //        for (Overlap c : overlaps) {
    //        if (c.involves(s1, s2)) continue SIDE_B;
    //
    //        // Distance between centrepoints.
    //
    //
    //        Overlap o = new Overlap(s1, s2, area);
    //        overlaps.add(o);
    //    }
    //    }
    //
    //        // Now iterate through the listeners and report overlaps to them.
    //        for (OverlapListener ol : mOverlapListeners) {
    //        // TODO On a different thread??
    //        ol.notifyOverlap(overlaps);
    //    }
    //    }
    ///}

}