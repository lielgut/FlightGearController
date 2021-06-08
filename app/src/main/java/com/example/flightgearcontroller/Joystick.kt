package com.example.flightgearcontroller

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class Joystick @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr : Int = 0) : View(context, attrs, defStyleAttr) {

    lateinit var onChange : (Float, Float) -> Unit;
    private val paintOuter = Paint().apply {
        style = Paint.Style.STROKE;
        color = Color.parseColor("#000000");
        isAntiAlias = true;
    }
    private val paintInner = Paint().apply {
        style = Paint.Style.FILL;
        color = Color.parseColor("#000000");
        isAntiAlias = true;
    }

    private var radius: Float = 0F;
    private var centerOuter: PointF = PointF();
    private var centerInner: PointF = PointF();

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = 0.3f * minOf(w, h).toFloat();
        centerOuter = PointF(w/2F, h/2F);
        centerInner = PointF(w/2F, h/2F);
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(centerOuter.x,centerOuter.y,radius,paintOuter);
        canvas.drawCircle(centerInner.x,centerInner.y,radius/2,paintInner);
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event == null) {
            return true;
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> Log.v("down","DOWWWN");
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> Log.v("up","UPPPPP");
        }
        return true;
    }

    private fun touchMove(x : Float, y: Float) {
        Log.v("moving","MOVVINGGGGG ($x,$y)");
        var changed = false;
        if(x >= centerOuter.x - radius/2 && x <= centerOuter.x + radius/2) {
            centerInner.x = x;
            changed = true;
        }
        if(y >= centerOuter.y - radius/2 && y <= centerOuter.y + radius/2) {
            centerInner.y = y;
            changed = true;
        }
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
            onChange(xPos,yPos);
        }
        invalidate();
    }
}