package com.aelfattah.ahmed.currency.ui.feature.convertCurrency

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.aelfattah.ahmed.currency.R
import com.aelfattah.ahmed.currency.domain.model.Currency

class CurrencyArrayAdapter(context: Context,
    currenciesList: MutableList<Currency>) :
    ArrayAdapter<Currency>(context, 0, currenciesList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        initView(position, convertView, parent)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        initView(position, convertView, parent)

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val item = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_dropdown, parent, false)

        (item.findViewById<TextView>(R.id.code)).text = getItem(position)?.code
        (item.findViewById<TextView>(R.id.name)).text = getItem(position)?.name

        item.tag = getItem(position)?.code

        return item
    }
}