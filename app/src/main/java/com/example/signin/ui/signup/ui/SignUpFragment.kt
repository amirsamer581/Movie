package com.example.signin.ui.signup.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.constants.KeyConstant.DATE_OF_BIRTH
import com.example.signin.constants.KeyConstant.ENTER_EMAIL
import com.example.signin.constants.KeyConstant.ENTER_PASSWORD
import com.example.signin.constants.KeyConstant.FIRST_NAME
import com.example.signin.constants.KeyConstant.LAST_NAME
import com.example.signin.constants.KeyConstant.SIGN_UP_SUCCESSFUL
import com.example.signin.databinding.FragmentSignUpBinding
import com.example.signin.domain.model.UserEntity
import com.example.signin.ui.signup.ui.commonfeatures.SignUpSnackBar
import com.example.signin.ui.signup.ui.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * The SignUpFragment is responsible for handling user registration.
 * It provides a user interface for users to input their details,
 * validates the input, registers the user using the SignUpViewModel,
 * and navigates to the HomeFragment upon successful registration.
 */
@AndroidEntryPoint
class SignUpFragment: Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel : SignUpViewModel by viewModels()
    private lateinit var statusSnackBar: SignUpSnackBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUISnackBar()
        setupClickListeners()

    }

    private fun setupClickListeners() {
        binding.signUpButton.setOnClickListener {
            val userDetails = getUserDetailFromInput()
            if (isUserInputValid(userDetails)) {
                navigateUserToHomeFragment(userDetails)
            }else {
                showInputErrors(userDetails)
            }
        }
    }

    private fun setupUISnackBar() {
        statusSnackBar = SignUpSnackBar()
    }

    private fun navigateUserToHomeFragment(userDetails: UserEntity) {
        signUpViewModel.registerUser(userDetails)
        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        statusSnackBar.showSuccess(requireView(),SIGN_UP_SUCCESSFUL)
    }

    private fun getUserDetailFromInput(): UserEntity {
        return UserEntity(
            firstName = binding.firstNameEditText.text.toString(),
            lastName = binding.lastNameEditText.text.toString(),
            email = binding.emailEditTextSignUp.text.toString(),
            password = binding.passwordEditTextSignUp.text.toString(),
            dateOfBirth = binding.dateOfBirthEditText.text.toString()
        )
    }

    private fun isUserInputValid(checkedUser: UserEntity): Boolean {
        return checkedUser.firstName.isNotEmpty() &&
                checkedUser.lastName.isNotEmpty() &&
                checkedUser.email.isNotEmpty() &&
                checkedUser.password.isNotEmpty() &&
                checkedUser.dateOfBirth.isNotEmpty()
    }

    private fun showInputErrors(checkedUser: UserEntity) {
        when {
            checkedUser.firstName.isEmpty() -> binding.firstNameEditText.error = FIRST_NAME
            checkedUser.lastName.isEmpty() -> binding.lastNameEditText.error = LAST_NAME
            checkedUser.email.isEmpty() -> binding.emailEditTextSignUp.error = ENTER_EMAIL
            checkedUser.password.isEmpty() -> binding.passwordEditTextSignUp.error = ENTER_PASSWORD
            checkedUser.dateOfBirth.isEmpty() -> binding.dateOfBirthEditText.error = DATE_OF_BIRTH
        }
    }
}