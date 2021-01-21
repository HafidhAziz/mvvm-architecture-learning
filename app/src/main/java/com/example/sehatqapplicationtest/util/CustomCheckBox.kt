package com.example.sehatqapplicationtest.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.example.sehatqapplicationtest.R

/**
 * Created by M Hafidh Abdul Aziz on 21/01/21.
 */

class CustomCheckBox : AppCompatCheckBox {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        this.setButtonDrawable(R.drawable.selector_chechbox)
    }

    override fun setChecked(t: Boolean) {
        if (t) {
            setBackgroundResource(R.drawable.checkbox_selected)
        } else {
            setBackgroundResource(R.drawable.checkbox_normal)
        }
        super.setChecked(t)
    }
}