package com.example.signin.ui.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.data.AppDatabase
import com.example.signin.data.UserRepository
import com.example.signin.databinding.FragmentLoginBinding
import com.example.signin.ui.login.commonfeatures.LogInSnackBar
import com.example.signin.viewmodel.LogInViewModel
import com.example.signin.viewmodel.LogInViewModelFactory

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val userRepository: UserRepository by lazy {
        UserRepository(
            AppDatabase.getInstance(requireContext()).userDao()
        )
    }
    private val viewModel: LogInViewModel by viewModels { LogInViewModelFactory(userRepository) }
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

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            } else {
                snackBar.allFields(requireView(),"Please fill all the fields")
            }
        }
        viewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                snackBar.showSuccess(requireView(),"Login successful")
            } else {
                snackBar.showError(requireView(),"Invalid email or password!")
            }
        }

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}