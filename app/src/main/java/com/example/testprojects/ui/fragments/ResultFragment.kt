package com.example.testprojects.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.testprojects.R
import com.example.testprojects.databinding.FragmentResultBinding
import com.example.testprojects.ui.viewmodel.QuizViewModel
import com.example.testprojects.utils.CommonUtils

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        CommonUtils.setupToolbar(binding.toolbar, requireActivity(), "Quiz Result")

        val total = viewModel.questions.value?.size ?: 0
        binding.tvCorrectScore.text = "${viewModel.correctAnswers} / $total"
        binding.tvStreakValue.text = "${viewModel.longestStreak}"

        binding.btnRestart.setOnClickListener {
            viewModel.resetQuiz()
            findNavController().navigate(R.id.action_resultFragment_to_quizFragment)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}