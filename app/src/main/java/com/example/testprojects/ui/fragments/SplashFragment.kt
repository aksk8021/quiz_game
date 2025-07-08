package com.example.testprojects.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.testprojects.R
import com.example.testprojects.databinding.FragmentSplashBinding
import com.example.testprojects.ui.viewmodel.QuizViewModel

class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    val quizViewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeViewModel()
        quizViewModel.fetchQuestions()
    }

    private fun observeViewModel() {
        quizViewModel.questions.observe(viewLifecycleOwner) { questions ->
            if (!questions.isNullOrEmpty()) {
                Handler(Looper.getMainLooper()).postDelayed({
                    if (isAdded && findNavController().currentDestination?.id ==
                        R.id.splashFragment) {
                        findNavController().navigate(R.id.action_splashFragment_to_quizFragment)
                    }
                }, 2000)
            }
        }

        quizViewModel.error.observe(viewLifecycleOwner) { errorMsg ->
            errorMsg?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}