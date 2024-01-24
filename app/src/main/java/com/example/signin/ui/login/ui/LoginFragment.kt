package com.example.signin.ui.login.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.signin.R
import com.example.signin.constants.SignInConstant.ENTER_EMAIL
import com.example.signin.constants.SignInConstant.ENTER_PASSWORD
import com.example.signin.constants.SignInConstant.INVALID_USER
import com.example.signin.constants.SignInConstant.KEY_PASSWORD_EMAIL1
import com.example.signin.constants.SignInConstant.KEY_USER_EMAIL1
import com.example.signin.constants.SignInConstant.LOG_IN_SUCCESSFUL
import com.example.signin.constants.SignInConstant.USER_PREFERENCES
import com.example.signin.databinding.FragmentLoginBinding
import com.example.signin.domain.model.UserEntity
import com.example.signin.ui.login.ui.commonfeatures.LogInSnackBar
import com.example.signin.ui.login.ui.viewmodel.DataStoreViewModel
import com.example.signin.ui.login.ui.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel : LogInViewModel by viewModels()
    private lateinit var snackBar: LogInSnackBar

//    private val Context.dataStore by preferencesDataStore(
//        name = USER_PREFERENCES
//    )
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES)
    private val dataStoreViewModel : DataStoreViewModel by viewModels()

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
            viewModel.loggedInUserSharedFlow.collect { user ->
                 if (user != null) {
                     //saveDataToDataStore(user.email, user.password)
                     dataStoreViewModel.saveEmail(binding.emailEditText.text.toString())
                     dataStoreViewModel.savePassword(binding.passwordEditText.text.toString())
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    snackBar.showSuccess(requireView(), LOG_IN_SUCCESSFUL)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.loggedInUserStateFlow.collect{
                //loadDataFromDataStore()
                binding.emailEditText.setText(dataStoreViewModel.getEmail())
                binding.passwordEditText.setText(dataStoreViewModel.getPassword())
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
                    viewModel.loggedInUserStateFlow.collect { user ->
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
    private suspend fun saveDataToDataStore(email: String, password: String) {
        requireContext().dataStore.edit { preferences ->
            preferences[KEY_USER_EMAIL1] = email
            preferences[KEY_PASSWORD_EMAIL1] = password
        }
    }

//    private suspend fun loadDataFromDataStore() {
//        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
//            val email = requireContext().dataStore.data
//                .map { preferences ->
//                    preferences[KEY_USER_EMAIL].toString() ?: ""
//                }.single()
//
//            val password = requireContext().dataStore.data
//                .map { preferences ->
//                    preferences[KEY_PASSWORD_EMAIL].toString() ?: ""
//                }.single()
//
//                binding.emailEditText.setText(email)
//                binding.passwordEditText.setText(password)
//        }
//    }


    private suspend fun loadDataFromDataStore() {
        val preferences = requireContext().dataStore.data.first()
        val email = preferences[KEY_USER_EMAIL1].toString()
        val password = preferences[KEY_PASSWORD_EMAIL1].toString()

            binding.emailEditText.setText(email)
            binding.passwordEditText.setText(password)
    }

}