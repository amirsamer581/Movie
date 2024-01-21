package com.example.signin.ui.login.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.constants.SignInConstant.ENTER_EMAIL
import com.example.signin.constants.SignInConstant.ENTER_PASSWORD
import com.example.signin.constants.SignInConstant.INVALID_USER
import com.example.signin.constants.SignInConstant.LOG_IN_SUCCESSFUL
import com.example.signin.databinding.FragmentLoginBinding
import com.example.signin.domain.model.UserEntity
import com.example.signin.ui.login.ui.commonfeatures.LogInSnackBar
import com.example.signin.ui.login.ui.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LogInViewModel by viewModels()
    private lateinit var snackBar: LogInSnackBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBar = LogInSnackBar()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.loggedInUser.collect { user ->
                if (user != null) {//todo make it user!!.email and user!!.password try this solution
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    snackBar.showSuccess(requireView(), LOG_IN_SUCCESSFUL)
                }
            }
        }

        binding.loginButton.setOnClickListener {
            val userData = UserEntity(
                firstName = "",
                lastName = "",
                email = binding.emailEditText.text.toString(),
                password = binding.passwordEditText.text.toString(),
                dateOfBirth = ""
            )

            if (userData.email.isNotEmpty() && userData.password.isNotEmpty()) {
                viewModel.login(userData.email, userData.password)
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.loggedInUser.collect { user ->
                        if (user == null)
                            snackBar.showError(requireView(), INVALID_USER)
                    }
                }
            } else {
                if (userData.email.isEmpty()) {
                    binding.emailEditText.error = ENTER_EMAIL
                }
                if (userData.password.isEmpty()) {
                    binding.passwordEditText.error = ENTER_PASSWORD
                }
            }
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}