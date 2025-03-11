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
import com.example.signin.constants.KeyConstant.ENTER_EMAIL
import com.example.signin.constants.KeyConstant.ENTER_PASSWORD
import com.example.signin.constants.KeyConstant.INVALID_USER
import com.example.signin.constants.KeyConstant.LOG_IN_SUCCESSFUL
import com.example.signin.databinding.FragmentLoginBinding
import com.example.signin.ui.login.ui.commonfeatures.LogInSnackBar
import com.example.signin.ui.login.ui.viewmodel.DataStoreViewModel
import com.example.signin.ui.login.ui.viewmodel.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A Fragment representing the login screen.
 *
 * This fragment handles user authentication by interacting with a [LogInViewModel]
 * and storing user credentials with a [DataStoreViewModel]. It also manages
 * navigation to the Sign Up and Home screens.
 *
 * The fragment observes data changes from the view models and updates the UI
 * accordingly. It also handles user interactions with the login and sign-up buttons.
 *
 * @property binding The view binding for the login fragment layout.
 * @property viewModel The [LogInViewModel] responsible for handling login logic.
 * @property snackBar The [LogInSnackBar] used to display feedback messages to the user.
 * @property dataStoreViewModel The [DataStoreViewModel] responsible for storing and retrieving data in DataStore.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LogInViewModel by viewModels()  // by viewModels() view models will automatically construct the viewModels using Hilt
    private lateinit var snackBar: LogInSnackBar
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUISnackBar()
        setupClickListeners()
        setupObservers()
    }

    private fun setupUISnackBar() {
        snackBar = LogInSnackBar()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            dataStoreViewModel.emailData.collect { email ->
                binding.emailEditText.setText(email)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            dataStoreViewModel.passwordData.collect { password ->
                binding.passwordEditText.setText(password)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.loggedInUserSharedFlow.collect { user ->
                if (user != null) {
                    saveEmail(binding.emailEditText.text.toString())
                    savePassword(binding.passwordEditText.text.toString())
                    navigateToHomeFragment()
                }
            }
        }
    }

    private fun setupClickListeners(){
        binding.signUpButton.setOnClickListener {
            navigateToSignUpFragment()
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
                    viewModel.loggedInUserStateFlow.collect { user ->
                        if (user == null) {
                            snackBar.showError(requireView(), INVALID_USER)
                        }
                    }
                }
            } else {
                if (email.isEmpty()) {
                    binding.emailEditText.error = ENTER_EMAIL
                }
                if (password.isEmpty()) {
                    binding.passwordEditText.error = ENTER_PASSWORD
                }
            }
        }
    }

    private fun saveEmail(email: String) {
        dataStoreViewModel.saveEmail(email)
    }

    private fun savePassword(password: String) {
        dataStoreViewModel.savePassword(password)
    }

    private fun navigateToSignUpFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

    private fun navigateToHomeFragment() {
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        snackBar.showSuccess(requireView(), LOG_IN_SUCCESSFUL)
    }
}