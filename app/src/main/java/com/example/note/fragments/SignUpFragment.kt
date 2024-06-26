package com.example.note.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.note.R
import com.example.note.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding: FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }
    private fun init(view: View){
        navControl= Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()

    }
    private  fun registerEvents(){

        binding.sn.setOnClickListener{
            navControl.navigate(R.id.action_signUpFragment_to_signInFragment)
        }


        binding.rccol4kpkapc.setOnClickListener{
            val email = binding.reo37xe8c6in.toString().trim()
            val pass = binding.rq2bwclhvx3.toString().trim()
            val verifyPass = binding.remaifkiah0f.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty() && verifyPass.isNotEmpty()){
                if (pass == verifyPass){
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Registered Successfuly!", Toast.LENGTH_SHORT)
                                .show()
                            navControl.navigate(R.id.action_signUpFragment_to_homeFragment)

                        } else {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }
}