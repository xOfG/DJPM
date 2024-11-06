package com.example.spacefighter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

class Projectile(var x: Int, var y: Int, var speed: Int) {

    private val width = 80 // Width of the projectile
    private val height = 15 // Height of the projectile
    private val paint = Paint().apply {
        color = Color.GREEN // Set the color of the projectile
    }

    // Rectangle for collision detection
    var detectCollision: Rect = Rect(x, y, x + width, y + height)

    fun update() {
        // Move the projectile to the right
        x += speed
        // Update the collision rectangle
        detectCollision.set(x, y, x + width, y + height)
    }

    fun draw(canvas: Canvas) {
        canvas.drawRect(detectCollision, paint)
    }
}