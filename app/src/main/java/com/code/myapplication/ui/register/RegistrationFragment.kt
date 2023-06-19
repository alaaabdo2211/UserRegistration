package com.code.myapplication.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.code.myapplication.R
import com.code.myapplication.databinding.FragmentRegistrationBinding
import com.code.myapplication.ui.MainActivity
import com.code.myapplication.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RegistrationFragment :
    BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private lateinit var viewModel: RegistrationViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                // Update the UI based on the state
                binding.etRegEmail.error = state.emailError?.let { context?.getString(it) }
                binding.etDateBirthReg.error =
                    state.dateOfBirthError?.let { context?.getString(it) }
                binding.etRegisterPassword.error =
                    state.passwordError?.let { context?.getString(it) }
                binding.etRegNationalId.error =
                    state.nationalIdError?.let { context?.getString(it) }
                binding.etRegPhone.error = state.phoneNumberError?.let { context?.getString(it) }
                binding.etRegName.error = state.fullNameError?.let { context?.getString(it) }

            }
        }

        binding.btnRegister.setOnClickListener {
            binding.run {
                if (viewModel.checkIfExist(binding.etRegEmail.text.toString())) {
                    binding.emailRegTextField.error = resources.getString(R.string.email_exist)
                } else {
                    viewModel.submitData(
                        etRegName.text.toString(),
                        etRegEmail.text.toString(),
                        etRegNationalId.text.toString(),
                        etRegPhone.text.toString(),
                        etDateBirthReg.text.toString(),
                        etRegisterPassword.text.toString()
                    )
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }


    }


}