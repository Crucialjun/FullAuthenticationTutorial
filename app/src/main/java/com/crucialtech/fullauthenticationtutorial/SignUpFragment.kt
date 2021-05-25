package com.crucialtech.fullauthenticationtutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.crucialtech.fullauthenticationtutorial.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null

    private var isDataValid : Boolean = false

    private lateinit var firebaseAuth : FirebaseAuth

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        val view = binding.root

        firebaseAuth = FirebaseAuth.getInstance()

        validateData(binding.registerFullName)
        validateData(binding.registerEmail)
        validateData(binding.registerPassword)
        validateData(binding.registerConfirmPassword)
        validateData(binding.registerPhoneNumber)
        validateData(binding.registerCountryCode)


        if(binding.registerPassword.text.toString() != binding.registerConfirmPassword.text.toString()){
            isDataValid = false
            binding.registerConfirmPassword.error = ("Password do not match")
        }else{
            isDataValid = true
        }

        if(isDataValid){
            firebaseAuth.createUserWithEmailAndPassword(binding.registerEmail.text.toString(),
                binding.registerConfirmPassword.text.toString()).addOnSuccessListener {
                Toast.makeText(requireActivity(), "User Account is created", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(requireContext(), "Error ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }

        return view

        
    }

    fun validateData(field : EditText){
        if (field.text.isNullOrEmpty()){
            isDataValid = false
            field.error = "Required Field"
        }else{
            isDataValid = true
        }
    }




    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}