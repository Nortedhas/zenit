package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class RegistrationTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputRegistration by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
        textInput.setBoxCornerRadii(12F,12F,12F,12F)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 20F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    init {

        renderUI()
    }

}

fun RegistrationTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputRegistration
    )

    textInputRegistration
        .constrainTopToTopOf(constraintLayout,25)
        .fillHorizontally(16)
        .height(55)
}

fun RegistrationTextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputRegistration.hint = hint
    textInputRegistration.defineType(type)
}