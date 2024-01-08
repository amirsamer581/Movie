package com.example.signin.ui.signup.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.data.UserRepository
import com.example.signin.data.local.AppDatabase
import com.example.signin.databinding.FragmentSignUpBinding
import com.example.signin.domain.UserEntity
import com.example.signin.ui.signup.domain.usecase.SignUpUseCase
import com.example.signin.ui.signup.ui.commonfeatures.SignUpSnackBar
import com.example.signin.ui.signup.viewmodel.SignUpViewModel
import com.example.signin.ui.signup.viewmodel.factory.SignUpViewModelFactory

class SignUpFragment : Fragment() {
    private val userRepository: UserRepository by lazy {
        UserRepository(
            AppDatabase.getInstance(requireContext()).userDao()
        )
    }
    private val signUpUseCase: SignUpUseCase by lazy {
        SignUpUseCase(userRepository)
    }
    private val signUpViewModel: SignUpViewModel by viewModels { SignUpViewModelFactory(signUpUseCase) }//TODO what is the usage for (by )
    private lateinit var snackBar: SignUpSnackBar
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackBar = SignUpSnackBar()

        binding.signUpButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val email = binding.emailEditTextSignUp.text.toString()
            val password = binding.passwordEditTextSignUp.text.toString()
            val dateOfBirth = binding.dateOfBirthEditText.text.toString()
            // TODO create class for the upper code

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() &&
                password.isNotEmpty() && dateOfBirth.isNotEmpty()
                ) {
                val user = UserEntity(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    password = password,
                    dateOfBirth = dateOfBirth
                )
                signUpViewModel.registerUser(user)
                findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                snackBar.showSuccess(requireView(),"Sign up successful")
            }else {
                if (firstName.isEmpty()) {
                    binding.firstNameEditText.error = "Please enter your first name"
                }
                if (lastName.isEmpty()) {
                    binding.lastNameEditText.error = "Please enter your last name"
                }
                if (email.isEmpty()) {
                    binding.emailEditTextSignUp.error = "Please enter your email"
                }
                if (password.isEmpty()) {
                    binding.passwordEditTextSignUp.error = "Please enter your password"
                }
                if (dateOfBirth.isEmpty()) {
                    binding.dateOfBirthEditText.error = "Please enter your date of birth"
                }
            }
        }
    }
}