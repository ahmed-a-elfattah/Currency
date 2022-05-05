package com.aelfattah.ahmed.currency.core.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    private var _binding: B? = null
    protected val binding: B get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getActivityBinding()
        setContentView(binding.root)
    }

    abstract fun getActivityBinding(): B

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}