package com.example.taskmeneger.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.example.taskmeneger.R
import com.example.taskmeneger.data.local.Pref
import com.example.taskmeneger.databinding.FragmentProfileragmentBinding
import com.github.dhaval2404.imagepicker.ImagePicker

class Profileragment : Fragment() {

        private lateinit var binding: FragmentProfileragmentBinding
        private val pref:Pref by lazy {
            Pref(requireContext())
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentProfileragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etName.setText(pref.getName())
        binding.etName.addTextChangedListener {
            pref.seveName(binding.etName.text.toString())
        }
        binding.imgProfile.setImageURI(pref.getImage().toString().toUri())
        binding.imgProfile.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.imgProfile.setImageURI(data?.data)
        pref.seveImage(data?.data.toString())
    }

}