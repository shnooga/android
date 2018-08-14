package com.slewsoft.boomcalc

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.preference.PreferenceManager
import android.view.View
import android.widget.EditText

class UserSettingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_setting)
    }

    override fun onStart() {
        super.onStart()

        val vertOffsetVal = getPreferenceInt(R.string.hinge_pin_vert_offset, 0)
        val horizOffsetVal = getPreferenceInt(R.string.hinge_pin_horiz_offset, 0)

        findViewById<EditText>(R.id.hingeOffsetVert).setText(vertOffsetVal.toString())
        findViewById<EditText>(R.id.hingeOffsetHoriz).setText(horizOffsetVal.toString())
    }

    private fun getPreferenceInt(id: Int, defaultVal: Int): Int {
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        return preference.getInt(getString(id), defaultVal)
    }

    private fun getInputVal(id: Int): Int { return findViewById<EditText>(id).text.toString().toInt() }

    fun onUpdate(view: View) {
        val preference = PreferenceManager.getDefaultSharedPreferences(this)

        val vertOffset = getInputVal(R.id.hingeOffsetVert)
        val horizOffset = getInputVal(R.id.hingeOffsetHoriz)

        with( preference.edit()) {
            putInt(getString(R.string.hinge_pin_horiz_offset), horizOffset)
            putInt(getString(R.string.hinge_pin_vert_offset), vertOffset)
            commit()
        }
    }

}
