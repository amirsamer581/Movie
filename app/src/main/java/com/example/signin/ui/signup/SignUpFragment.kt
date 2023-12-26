package com.example.signin.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.signin.data.UserEntity
import com.example.signin.databinding.FragmentSignUpBinding
import com.example.signin.viewmodel.AuthViewModel

class SignUpFragment : Fragment() {
    private val viewModel: AuthViewModel by viewModels()
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

        binding.signUpButton.setOnClickListener {
            val firstName = binding.firstNameEditText.text.toString()
            val lastName = binding.lastNameEditText.text.toString()
            val email = binding.emailEditTextSignUp.text.toString()
            val password = binding.passwordEditTextSignUp.text.toString()
            val dob = binding.dateOfBirthEditText.text.toString()

            val user = UserEntity(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password,
                dateOfBirth = dob
            )
            viewModel.registerUser(user)

            //findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
        }

    }
}