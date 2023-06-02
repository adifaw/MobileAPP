package com.example.challenge_chapter6_fix.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.ViewModelFactory
import com.example.challenge_chapter6_fix.data.DataUserManager
import com.example.challenge_chapter6_fix.databinding.FragmentRegisterBinding
import com.example.challenge_chapter6_fix.utils.RegisterUtils
import com.example.challenge_chapter6_fix.viewModel.UserViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var pref: DataUserManager
    private lateinit var viewModel: UserViewModel

    private lateinit var auth: FirebaseAuth
    private lateinit var analytics: FirebaseAnalytics
    private lateinit var register: RegisterUtils

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        register = RegisterUtils
        auth = Firebase.auth
        analytics = Firebase.analytics
        pref = DataUserManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener{
            binding.apply {
                registerBtn()
            }
        }

        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
    fun registerBtn (){
        val username = binding.txtUsername.text.toString()
        val name = binding.txtName.text.toString()
        val email = binding.txtEmail.text.toString()
        val birthday = binding.txtBirthday.text.toString()
        val nomor = binding.txtPhone.text.toString()
        val password = binding.txtPassword.text.toString()
        val confirmPassword = binding.txtConfirmPassword.text.toString()

        if (register.validate(username, name, email, birthday, nomor, password, confirmPassword)){
            viewModel.saveUser(username, name, email, birthday, nomor, password)
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(requireContext(), "Password Not Match", Toast.LENGTH_SHORT).show()
    }
}