package com.slewsoft.boomcalc

import android.app.Activity
import android.os.Bundle
import android.support.constraint.Group
import android.view.View
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : Activity() {

    private val jibCbListener = View.OnClickListener { jibAdjustChecked() }
    private val insertCbListener = View.OnClickListener { insertAdjustChecked() }
//    private var jibCheckBox = null//= findViewById<CheckBox>(R.id.jib_adj_cb)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jibCheckBox = findViewById<CheckBox>(R.id.jib_adj_cb)
        jibCheckBox.setOnClickListener(jibCbListener)

        val insertCheckBox = findViewById<CheckBox>(R.id.insert_adj_cb)
        insertCheckBox.setOnClickListener(insertCbListener)
    }

    private fun jibAdjustChecked() {

        val jibCheckBox = findViewById<CheckBox>(R.id.jib_adj_cb)
        val jibGroup = findViewById<Group>(R.id.jib_grp)
        System.out.println("boom " + jibCheckBox.isChecked())

        if (jibCheckBox.isChecked()) {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.VISIBLE
        } else {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.INVISIBLE
        }
        System.out.println("boom " + jibCheckBox.visibility)
    }

    private fun insertAdjustChecked() {

        val jibCheckBox = findViewById<CheckBox>(R.id.insert_adj_cb)
        val jibGroup = findViewById<Group>(R.id.insert_grp)
        System.out.println("boom " + jibCheckBox.isChecked())

        if (jibCheckBox.isChecked()) {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.VISIBLE
        } else {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.INVISIBLE
        }
        System.out.println("boom " + jibCheckBox.visibility)
    }

    fun calculate(view: View) {
        val boomLen = findViewById<EditText>(R.id.boom_length).text.toString().toDouble()
        val baseHeight = findViewById<EditText>(R.id.crane_base_height).text.toString().toDouble()
        val bldHeight = findViewById<EditText>(R.id.bldg_heigth).text.toString().toDouble()
        val bldOffset = findViewById<EditText>(R.id.bldg_offset).text.toString().toDouble()
        val adjBldHeight = bldHeight - baseHeight
        val boomAngle = Math.atan(adjBldHeight / bldOffset) * 180 / Math.PI
        val hypot = Math.hypot(bldOffset, adjBldHeight)
        val objectOffSet = bldOffset * (boomLen - hypot) / hypot;




        findViewById<EditText>(R.id.object_offset).setText(objectOffSet.toString())
        findViewById<EditText>(R.id.boom_angle).setText(boomAngle.toString())
    }

}
