package com.gyhun.barrierfree.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gyhun.barrierfree.HomeActivity
import com.gyhun.barrierfree.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.gyhun.barrierfree.R

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setOnClickListener() {
        binding.btnSignIn.setOnClickListener {
            val userId = binding.loginIdTextFieldEditText.text.toString()
            val userPassword = binding.loginPasswordTextFieldEditText.text.toString()
            signIn(userId, userPassword)
        }

        binding.btnCreateAccount.setOnClickListener {
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
    }

    private fun signIn(userId: String, userPassword: String) {
        if (userId.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.input_is_empty), Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(userId, userPassword).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(),
                        getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), getString(R.string.Check_Email_Password), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}