package com.onix.internship.survay.ui.data.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.onix.internship.survay.R
import com.onix.internship.survay.arch.error.ErrorStates

@BindingAdapter("showError")
fun TextInputLayout.showError(errorStates: ErrorStates) {
    error = when (errorStates) {
        ErrorStates.NONE -> ""
        ErrorStates.INCORRECT_DATA -> context.getString(R.string.incorrect_data)
        ErrorStates.EMPTY_FIELD -> context.getString(R.string.empty_field)
        ErrorStates.USER_ALREADY_REGISTERED -> context.getString(R.string.user_already_registered)
        ErrorStates.UNCONFIRMED_PASSWORD -> context.getString(R.string.passwords_unconfirmed)
    }
}
