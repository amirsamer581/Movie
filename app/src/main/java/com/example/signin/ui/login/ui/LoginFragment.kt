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
import com.example.signin.data.local.AppDatabase
import com.example.signin.databinding.FragmentLoginBinding
import com.example.signin.ui.login.domain.usecase.LogInUseCase
import com.example.signin.ui.login.ui.commonfeatures.LogInSnackBar
import com.example.signin.ui.login.viewmodel.LogInViewModel
import com.example.signin.ui.login.viewmodel.factory.LogInViewModelFactory
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val userRepository: UserRepository by lazy {
        UserRepository(
            AppDatabase.getInstance(requireContext()).userDao()
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

//        logInViewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
//            if (user != null) {
//                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//                snackBar.showSuccess(requireView(), "Login successful")
//            } else {
//                snackBar.showError(requireView(), "Invalid email or password !")
//            }
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            logInViewModel.loggedInUser.collect { user ->
                if (user != null) {
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    snackBar.showSuccess(requireView(), "Login successful")
                } else {
                    snackBar.showError(requireView(), "Invalid email or password!")//TODO fix the issue for this message
                }
            }
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                logInViewModel.login(email, password)
            } else {
                if (email.isEmpty()) {
                    binding.emailEditText.error = "Please enter your email"
                }
                if (password.isEmpty()) {
                    binding.passwordEditText.error = "Please enter your password"
                }
            }
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}