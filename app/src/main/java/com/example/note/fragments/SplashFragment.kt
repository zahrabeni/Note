//package com.example.note.fragments
//
//import android.os.Bundle
//import android.os.Handler
//import android.os.Looper
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.NavController
//import androidx.navigation.Navigation
//import com.example.note.R
//import com.google.firebase.auth.FirebaseAuth
//
//class SplashFragment : Fragment() {
//
//    private lateinit var auth: FirebaseAuth
//    private lateinit var navController: NavController
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_splash, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//       // val isLogin: Boolean = mAuth.currentUser != null
//        auth = FirebaseAuth.getInstance()
//        navController = Navigation.findNavController(view)
//        Handler(Looper.myLooper()!!).postDelayed({
//            if (auth.currentUser != null){
//                navController.navigate(R.id.action_splashFragment_to_homeFragment)
//            } else{
//                navController.navigate(R.id.action_splashFragment_to_signInFragment)
//            }
//        },2000)
//
//    }
//}
package com.example.note.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.note.R
import android.widget.Button
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import android.util.Log


class SplashFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)

        // Using Handler with the main looper directly
        Handler(Looper.getMainLooper()).postDelayed({
            if (auth.currentUser != null) {
                navController.navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
                navController.navigate(R.id.action_splashFragment_to_signInFragment)
            }
        }, 2000)
        // Add the click listener for the start button
        val startButton: Button = view.findViewById(R.id.startButton)
        startButton.setOnClickListener {
            Log.d("SplashFragment", "Start button clicked")
            navController.navigate(R.id.action_splashFragment_to_signInFragment)
        }

    }
}
