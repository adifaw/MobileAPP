package com.example.challenge_chapter6_fix.ui

import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.ViewModelFactory
import com.example.challenge_chapter6_fix.data.DataUserManager
import com.example.challenge_chapter6_fix.databinding.FragmentLoginBinding
import com.example.challenge_chapter6_fix.utils.LoginUtils
import com.example.challenge_chapter6_fix.viewModel.UserViewModel
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: DataUserManager
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var auth: FirebaseAuth
    private lateinit var login: LoginUtils


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        login = LoginUtils
        auth = Firebase.auth
        analytics = Firebase.analytics
        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener{
            val _email = binding.email.text.toString()
            val _password = binding.password.text.toString()

            if(login.validate(_email, _password)){
                viewModel.setIsLogin(true)
                auth.signInWithEmailAndPassword(_email, _password)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            val user = auth.currentUser
                            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(context, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else {
                Toast.makeText(
                    requireContext(),
                    "The username or password is incorrect!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
        ) { res ->
            onSignInResult(res)
        }

        binding.cardViewGoogle.setOnClickListener(){
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.PhoneBuilder().build()))
                .build()
            signInLauncher.launch(signInIntent)
        }
    }
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "Login gagal", Toast.LENGTH_LONG).show()
        }
    }


}