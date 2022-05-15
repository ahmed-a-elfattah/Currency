package com.aelfattah.ahmed.currency.ui

import com.aelfattah.ahmed.currency.core.common.BaseActivity
import com.aelfattah.ahmed.currency.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getActivityBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)
}