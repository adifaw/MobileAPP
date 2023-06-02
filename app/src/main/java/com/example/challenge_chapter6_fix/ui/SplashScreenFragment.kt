package com.example.challenge_chapter6_fix.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter6_fix.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreenFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        auth = Firebase.auth
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = auth.currentUser
        Handler(Looper.myLooper()!!).postDelayed({
            if(user != null)
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
            else
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
        },2000)

    }

}

