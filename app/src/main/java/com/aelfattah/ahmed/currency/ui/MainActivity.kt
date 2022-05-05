package com.aelfattah.ahmed.currency.ui

import android.os.Bundle
import android.util.Log
import com.aelfattah.ahmed.currency.core.common.BaseActivity
import com.aelfattah.ahmed.currency.core.common.DataState
import com.aelfattah.ahmed.currency.databinding.ActivityMainBinding
import com.aelfattah.ahmed.currency.domain.usecase.GetCurrenciesUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun getActivityBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    @Inject
    lateinit var getCurrenciesUseCase: GetCurrenciesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.IO) {
            getCurrenciesUseCase.invoke().collect {
                when (it) {
                    is DataState.Loading -> {
                        Log.e(TAG, "onCreate: Loading")
                    }
                    is DataState.Error -> {
                        Log.e(TAG, "onCreate: Error ${it.message}")
                    }
                    is DataState.Success -> {
                        Log.e(TAG, "onCreate: Success ${it.data}")
                    }
                }
            }
        }
    }
}