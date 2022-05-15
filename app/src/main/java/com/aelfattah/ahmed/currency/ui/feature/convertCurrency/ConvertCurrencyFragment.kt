package com.aelfattah.ahmed.currency.ui.feature.convertCurrency

import android.database.DataSetObserver
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import com.aelfattah.ahmed.currency.core.common.BaseFragment
import com.aelfattah.ahmed.currency.databinding.FragmentConvertCurrencyBinding
import com.aelfattah.ahmed.currency.domain.model.Currency
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConvertCurrencyFragment :
    BaseFragment<FragmentConvertCurrencyBinding, ConvertCurrencyViewModel>(
        FragmentConvertCurrencyBinding::inflate
    ) {

    companion object {
        private const val TAG = "ConvertCurrencyFragment"
    }

    override val viewModel: ConvertCurrencyViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_LONG).show()
        }

        viewModel.currencies.observe(viewLifecycleOwner) {
            val fromArrayAdapter = CurrencyArrayAdapter(requireContext(), it)
            val toArrayAdapter = CurrencyArrayAdapter(requireContext(), it)

            fromArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            toArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.fromDropdown.apply {
                adapter = fromArrayAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selected = parent.getItemAtPosition(position) as Currency
                        viewModel.changeFromCurrency(selected.code)
                        convert()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }
            binding.toDropdown.apply {
                adapter = toArrayAdapter
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selected = parent.getItemAtPosition(position) as Currency
                        viewModel.changeToCurrency(selected.code)
                        convert()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }
        }
    }

    fun convert() {
        val from = viewModel.fromCurrency.value
        val to = viewModel.toCurrency.value
        val amount = viewModel.amount.value as Float
        Log.e(TAG, "convert: $from $to $amount")
        if (!from.isNullOrEmpty() &&
            !to.isNullOrEmpty() &&
            amount >= 1f
        ) viewModel.convertCurrency()
    }
}
