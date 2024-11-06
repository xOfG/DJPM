package com.example.spacefighter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView : SurfaceView, Runnable {

    var playing = false
    var gameThread : Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas

    lateinit var paint :Paint
    var stars = arrayListOf<Star>()
    var enemies = arrayListOf<Enemy>()
    lateinit var player : Player
    lateinit var boom : Boom

    private fun init(context: Context, width: Int, height: Int){

        surfaceHolder = holder
        paint = Paint()

        for (i in 0..100){
            stars.add(Star(width, height))
        }

        for (i in 0..2){
            enemies.add(Enemy(context,width, height))
        }

        player = Player(context, width, height)
        boom = Boom(context, width, height)

    }

    constructor(context: Context?, width: Int, height: Int) : super(context) {
        init(context!!, width, height)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context!!, 0, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context!!, 0, 0)
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause() {
        playing = false
        gameThread?.join()
    }

    override fun run() {
        while (playing){
            update()
            draw()
            control()
        }
    }


    fun update() {
        // Reset boom position
        boom.x = -300
        boom.y = -300

        // Update stars
        for (s in stars) {
            s.update(player.speed)
        }

        // Update enemies and check for player collisions
        for (e in enemies) {
            e.update(player.speed)
            if (Rect.intersects(player.detectCollision, e.detectCollision)) {
                boom.x = e.x
                boom.y = e.y
                e.x = -300 // Move the enemy off-screen
            }
        }

        // Update projectiles and check for collisions with enemies
        for (i in player.projectiles.indices.reversed()) {
            val projectile = player.projectiles[i]
            projectile.update() // Update the projectile position
            for (e in enemies) {
                if (Rect.intersects(projectile.detectCollision, e.detectCollision)) {
                    // Handle collision
                    boom.x = e.x
                    boom.y = e.y
                    e.x = -300 // Move the enemy off-screen
                    player.projectiles.remove(projectile) // Remove the projectile
                    break // Exit the loop to avoid ConcurrentModificationException
                }
            }
        }

        // Update player
        player.update()
    }

    fun draw(){
        if (surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            // design code here

            canvas.drawColor(Color.BLACK)

            paint.color = Color.YELLOW

            for (star in stars) {
                paint.strokeWidth = star.starWidth.toFloat()
                canvas.drawPoint(star.x.toFloat(), star.y.toFloat(), paint)
            }

            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            for (e in enemies) {
                canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
            }
            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)

            // Draw projectiles
            for (projectile in player.projectiles) {
                projectile.draw(canvas)
            }

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                player.boosting = true
                player.shoot() // Shoot a projectile when the screen is touched
            }
            MotionEvent.ACTION_UP -> {
                player.boosting = false
            }
        }
        return true
    }

}