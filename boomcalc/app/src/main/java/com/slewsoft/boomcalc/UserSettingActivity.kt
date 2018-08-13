package com.slewsoft.boomcalc

import android.os.Bundle
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.EditText

class UserSettingActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_setting)
    }

    override fun onStart() {
        super.onStart()

        val vertOffsetVal = getPreferenceInt(R.string.hinge_pin_vert_offset, 10)
        val horizOffsetVal = getPreferenceInt(R.string.hinge_pin_horiz_offset, 6)

        findViewById<EditText>(R.id.hingeOffsetVert).setText(horizOffsetVal.toString())
        findViewById<EditText>(R.id.hingeOffsetHoriz).setText(vertOffsetVal.toString())
    }

    private fun getPreferenceInt(id: Int, defaultVal: Int): Int {
        val preference = this?.getPreferences(Context.MODE_PRIVATE) ?: return defaultVal
        return preference.getInt(getString(id), defaultVal)
    }

    private fun getInputVal(id: Int): Int { return findViewById<EditText>(id).text.toString().toInt() }

    fun onUpdate(view: View) {
        val preference = this?.getPreferences(Context.MODE_PRIVATE) ?: return

        val vertOffset = getInputVal(R.id.hingeOffsetVert)
        val horizOffset = getInputVal(R.id.hingeOffsetHoriz)

        with( preference.edit()) {
            putInt(getString(R.string.hinge_pin_horiz_offset), horizOffset)
            putInt(getString(R.string.hinge_pin_vert_offset), vertOffset)
            commit()
        }
    }

}
