package com.code.myapplication.ui.more

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.code.myapplication.databinding.FragmentMoreBinding
import com.code.myapplication.ui.AuthenticationActivity
import com.code.myapplication.util.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoreFragment : BaseFragment<FragmentMoreBinding>(FragmentMoreBinding::inflate) {

    private lateinit var viewModel: MoreViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MoreViewModel::class.java]

        binding.linearLogout.setOnClickListener {
            val editor: SharedPreferences.Editor =
                requireActivity().getSharedPreferences("name", MODE_PRIVATE).edit()
            editor.putString("password", "")
            editor.putString("email", "")
            editor.putBoolean("isLoggedIn", false)
            editor.apply()

            val intent = Intent(requireContext(), AuthenticationActivity::class.java)
            intent.putExtra("finish", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)

        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->

                binding.moreProgress.isVisible = state.isLoading
                binding.groupView.isVisible = !state.isLoading



                state.moreInfo?.let {
                    Log.d("data111", state.moreInfo.toString())
                    binding.groupView.visibility = View.VISIBLE
                    binding.moreProgress.visibility = View.GONE
                    binding.txtFullName.text = it.fullName
                    binding.txtEmail.text = it.email
                    binding.txtNationalIDNum.text = it.nationalId
                    binding.txtPhoneNumber.text = it.phoneNumber
                    binding.txtDateBirth.text = it.dateOfBirth
                }
            }
        }
    }
}