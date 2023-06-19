package com.code.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.code.myapplication.databinding.FragmentLoginBinding
import com.code.myapplication.ui.MainActivity
import com.code.myapplication.util.BaseFragment
import com.code.myapplication.util.UiSingleEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private lateinit var viewModel: LoginViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        lifecycleScope.launch {
            viewModel.uiEvent.collect {
                when (it) {
                    UiSingleEvent.NavigateToNextPage -> {
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                    }
                    is UiSingleEvent.ShowSnackBar -> {
                        Snackbar.make(
                            view,
                            requireContext().getString(it.messageResource),
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            var email = binding.etLoginEmail.text.toString()
            var password = binding.etLoginPassword.text.toString()
            viewModel.loginUser(email, password)
        }

    }
}