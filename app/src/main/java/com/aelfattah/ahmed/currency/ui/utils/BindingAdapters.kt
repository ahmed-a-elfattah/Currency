package com.aelfattah.ahmed.currency.ui.utils

import android.widget.EditText
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.util.*

object BindingAdapters {

    @BindingAdapter("app:amount")
    @JvmStatic
    fun bindAmount(editText: EditText, amount: Float) {
        editText.setText(NumberFormat.getInstance(Locale.US).format(amount))
    }
}