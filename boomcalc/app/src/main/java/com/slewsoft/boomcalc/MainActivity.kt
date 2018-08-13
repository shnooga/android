package com.slewsoft.boomcalc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.constraint.Group
import android.view.View
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : Activity() {

    private val jibCbListener = View.OnClickListener { jibAdjustChecked() }
    private val insertCbListener = View.OnClickListener { insertAdjustChecked() }

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

        if (jibCheckBox.isChecked()) {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.VISIBLE
        } else {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.INVISIBLE
        }
    }

    private fun insertAdjustChecked() {
        val jibCheckBox = findViewById<CheckBox>(R.id.insert_adj_cb)
        val jibGroup = findViewById<Group>(R.id.insert_grp)

        if (jibCheckBox.isChecked()) {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.VISIBLE
        } else {
            jibGroup.visibility = View.GONE
            jibGroup.visibility = View.INVISIBLE
        }
    }


    private fun getPreferenceInt(id: Int, defaultVal: Int): Int {
        val preference = this?.getPreferences(Context.MODE_PRIVATE) ?: return defaultVal
        return preference.getInt(getString(id), defaultVal)
    }
    private fun toDegree(radians: Double): Double { return radians * (180 / Math.PI); }
    private fun toRadian(degree: Double): Double { return degree * (Math.PI / 180); }
    private fun toTwoDecimal(num: Double): Double { return String.format("%.2f", num).toDouble() }
    private fun getInputVal(id: Int): Double { return findViewById<EditText>(id).text.toString().toDouble() }

    fun reset(view: View) {
        findViewById<EditText>(R.id.boom_length).setText("0")
        findViewById<EditText>(R.id.crane_base_height).setText("0")
        findViewById<EditText>(R.id.bldg_heigth).setText("0")
        findViewById<EditText>(R.id.bldg_offset).setText("0")

        findViewById<EditText>(R.id.jib_len).setText("0")
        findViewById<EditText>(R.id.jib_offset).setText("0")

        findViewById<EditText>(R.id.insert_len).setText("0")
        findViewById<EditText>(R.id.insert_qty).setText("0")

        findViewById<EditText>(R.id.boom_angle).setText("0")
        findViewById<EditText>(R.id.object_offset).setText("0")
        findViewById<EditText>(R.id.overall_radius).setText("0")
        findViewById<EditText>(R.id.overall_boom_len).setText("0")

    }

    fun calculate(view: View) {
        val vertOffsetVal = getPreferenceInt(R.string.hinge_pin_vert_offset, 10)
        val horizOffsetVal = getPreferenceInt(R.string.hinge_pin_horiz_offset, 6)
        val boomLen = getInputVal(R.id.boom_length)
//        val baseHeight = getInputVal(R.id.crane_base_height)
        val bldHeight = getInputVal(R.id.bldg_heigth)
        val bldOffset = getInputVal(R.id.bldg_offset)
        val adjBldHeight = bldHeight - vertOffsetVal
        val boomAngle = toTwoDecimal(Math.atan(adjBldHeight / bldOffset) * 180 / Math.PI)
        val hypot = Math.hypot(bldOffset, adjBldHeight)
        val objectOffSet = toTwoDecimal(bldOffset * (boomLen - hypot) / hypot)
        val jibOffsetAngle = Math.abs(boomAngle - getInputVal(R.id.jib_offset))
        val jibOffsetLen = Math.cos(toRadian(jibOffsetAngle)) * getInputVal(R.id.jib_len)
        val overallRadius = toTwoDecimal(bldOffset + objectOffSet + jibOffsetLen - horizOffsetVal)
        val insert_qty = getInputVal(R.id.insert_qty)
        val insert_len = getInputVal(R.id.insert_len)
        val overallBoomLen = toTwoDecimal(boomLen + (insert_qty * insert_len))

        println(jibOffsetLen)
        findViewById<EditText>(R.id.object_offset).setText(objectOffSet.toString())
        findViewById<EditText>(R.id.boom_angle).setText(boomAngle.toString())
        findViewById<EditText>(R.id.overall_radius).setText(overallRadius.toString())
        findViewById<EditText>(R.id.overall_boom_len).setText(overallBoomLen.toString())

        val cbGroup = findViewById<Group>(R.id.cb_group)

        cbGroup.visibility = View.GONE
        cbGroup.visibility = View.VISIBLE
    }

    fun editUserSettings(view: View) {
        val intent = Intent(this, UserSettingActivity::class.java)
        startActivity(intent)
    }

}
