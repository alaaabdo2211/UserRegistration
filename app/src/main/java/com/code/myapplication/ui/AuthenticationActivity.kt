package com.code.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.code.myapplication.R
import com.code.myapplication.databinding.ActivityAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController


        binding.txtLogin.setOnClickListener {
            navController.navigate(R.id.loginFragment2)
            binding.txtRegister.setTextAppearance(R.style.regular_black_medium)
            binding.txtRegister.setText(R.string.register)
            binding.txtLogin.setTextAppearance(R.style.bold_black_medium)
            binding.txtLogin.setText(R.string.underlined_login)

        }
        binding.txtRegister.setOnClickListener {
            navController.navigate(R.id.registrationFragment2)
            binding.txtRegister.setTextAppearance(R.style.bold_black_medium)
            binding.txtRegister.setText(R.string.underlined_register)
            binding.txtLogin.setTextAppearance(R.style.regular_black_medium)
            binding.txtLogin.setText(R.string.login)
        }
    }


}