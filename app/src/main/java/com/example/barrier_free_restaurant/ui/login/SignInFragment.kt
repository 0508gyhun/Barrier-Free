package com.example.barrier_free_restaurant.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.barrier_free_restaurant.HomeActivity
import com.example.barrier_free_restaurant.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
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
            Toast.makeText(requireContext(), "입력값이 비었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(userId, userPassword).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireContext(), "로그인 성공!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "이메일과 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}