package com.example.signin.ui.importobject.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.signin.constants.KeyConstant.LOCATION_NOT_ALLOW
import com.example.signin.databinding.FragmentImportObjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImportObject : Fragment() {
    private lateinit var binding: FragmentImportObjectBinding
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            binding.deviceImageView.setImageURI(result.data?.data)
        }
    }
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                binding.userLocation.text = permissions.toString()
            }

            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                binding.userLocation.text = permissions.toString()
            }

            else -> {
                binding.userLocation.text = LOCATION_NOT_ALLOW
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImportObjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnImportImage.setOnClickListener {
            chooseImageGallery()
        }

        binding.btnUserLocation.setOnClickListener {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun chooseImageGallery() {
//        val intent = Intent(Intent.ACTION_PICK)
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
//        startActivityForResult(intent,  IMAGE_REQUEST_CODE)
        imagePickerLauncher.launch(intent)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if(requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
//            binding.deviceImageView.setImageURI(data?.data)
//        }
//    }
//companion object {
//        private val IMAGE_REQUEST_CODE = 100
//        private val REQUEST_CODE = 123
//}

}