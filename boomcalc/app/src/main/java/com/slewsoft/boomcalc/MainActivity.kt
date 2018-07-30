package com.slewsoft.boomcalc

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calculate(view : View) {
        val boomLen = findViewById<EditText>(R.id.boom_length).text.toString().toDouble()
        val baseHeight = findViewById<EditText>(R.id.crane_base_height).text.toString().toDouble()
        val bldHeight = findViewById<EditText>(R.id.bldg_heigth).text.toString().toDouble()
        val bldOffset = findViewById<EditText>(R.id.bldg_offset).text.toString().toDouble()
        val adjBldHeight = bldHeight - baseHeight
        val boomAngle = Math.atan(adjBldHeight/bldOffset) * 180/Math.PI
        val hypot = Math.hypot(bldOffset, adjBldHeight)
        val objectOffSet = bldOffset * (boomLen - hypot) / hypot;




        findViewById<EditText>(R.id.object_offset).setText(objectOffSet.toString())
        findViewById<EditText>(R.id.boom_angle).setText(boomAngle.toString())
    }
}
