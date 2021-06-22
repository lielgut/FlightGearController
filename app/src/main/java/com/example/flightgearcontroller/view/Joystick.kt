package com.example.flightgearcontroller.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

// custom view for joystick
class Joystick @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr : Int = 0) : View(context, attrs, defStyleAttr) {

    lateinit var onChange : (Float, Float) -> Unit;
    private val paintOuter = Paint().apply {
        style = Paint.Style.FILL;
        color = Color.parseColor("#eee3ff");
        isAntiAlias = true;
    }
    private val paintInner = Paint().apply {
        style = Paint.Style.FILL;
        color = Color.parseColor("#6200ee");
        isAntiAlias = true;
    }

    private var radius: Float = 0F; // circle radius
    private var centerOuter: PointF = PointF(); // center of the external circle
    private var centerInner: PointF = PointF(); // center of the inner circle

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = 0.3f * minOf(w, h).toFloat();
        centerOuter = PointF(w/2F, h/2F);
        centerInner = PointF(w/2F, h/2F);
    }

    // draw two circles for the joystick
    override fun onDraw(canvas: Canvas) {
        radius = canvas.width.toFloat()/2.25F;
        canvas.drawCircle(centerOuter.x,centerOuter.y,radius,paintOuter);
        canvas.drawCircle(centerInner.x,centerInner.y,radius/2,paintInner);
    }

    // handling touch events on the joystick
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event == null) {
            return true;
        }
        when (event.action) {
            // event for when the joystick is being dragged
            MotionEvent.ACTION_MOVE -> dragJoystick(event.x, event.y)
        }
        return true;
    }

    // event for dragging the joystick
    private fun dragJoystick(x : Float, y: Float) {
        var changed = false;
        // keep inner circle within bounds
        if(x >= centerOuter.x - radius/2 && x <= centerOuter.x + radius/2) {
            centerInner.x = x;
            changed = true;
        }
        if(y >= centerOuter.y - radius/2 && y <= centerOuter.y + radius/2) {
            centerInner.y = y;
            changed = true;
        }
        // convert center of inner circle to value in [-1,1]x[-1,1]
        if(changed) {
            var xPos = (x - centerOuter.x) / radius;
            var yPos = -1  * ((y - centerOuter.y) / radius);
            if(xPos > 1F)
                xPos = 1F;
            if(xPos < -1F)
                xPos = -1F;
            if(yPos > 1F)
                yPos = 1F;
            if(yPos < -1F)
                yPos = -1F;
            // notify joystick has changed
            onChange(xPos,yPos);
        }
        invalidate();
    }
}