package com.gyhun.barrierfree.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gyhun.barrierfree.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnCreateAccount.setOnClickListener {
            val userId = binding.signupIdTextFieldEditText.text.toString()
            val userPassword = binding.signupPasswordTextFieldEditText.text.toString()
            registerAuth(userId, userPassword)
        }
        binding.signupTopAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun registerAuth(userId: String, userPassword: String) {
        if (userId.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(requireContext(), "빈값을 입력했습니다.", Toast.LENGTH_SHORT).show()
        } else {
            auth.createUserWithEmailAndPassword(userId, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(requireContext(), "회원가입 완료!!.", Toast.LENGTH_SHORT)
                            .show()
                        val action =
                            SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "아이디와 비밀번호를 확인해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}