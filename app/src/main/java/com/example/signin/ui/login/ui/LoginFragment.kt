package com.example.signin.ui.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.data.UserRepository
import com.example.signin.data.local.UserDatabase
import com.example.signin.databinding.FragmentLoginBinding
import com.example.signin.domain.UserEntity
import com.example.signin.ui.login.domain.usecase.LogInUseCase
import com.example.signin.ui.login.ui.commonfeatures.LogInSnackBar
import com.example.signin.ui.login.viewmodel.LogInViewModel
import com.example.signin.ui.login.viewmodel.factory.LogInViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val userRepository: UserRepository by lazy {
        UserRepository(
            UserDatabase.getInstance(requireContext()).userDao()
        )
    }
    private val loginUseCase: LogInUseCase by lazy {
        LogInUseCase(userRepository)
    }
    private val logInViewModel: LogInViewModel by viewModels { LogInViewModelFactory(loginUseCase) }
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

        viewLifecycleOwner.lifecycleScope.launch {
            logInViewModel.loggedInUser.collect { user ->
                if (user != null) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    snackBar.showSuccess(requireView(), "Login successful")
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
                logInViewModel.login(userData.email, userData.password)
                viewLifecycleOwner.lifecycleScope.launch {
                    logInViewModel.loggedInUser.collect { user ->
                        if (user == null)
                            snackBar.showError(requireView(), "Invalid email or password!")
                    }
                }
            } else {
                if (userData.email.isEmpty()) {
                    binding.emailEditText.error = "Please enter your email"
                }
                if (userData.password.isEmpty()) {
                    binding.passwordEditText.error = "Please enter your password"
                }
            }
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}