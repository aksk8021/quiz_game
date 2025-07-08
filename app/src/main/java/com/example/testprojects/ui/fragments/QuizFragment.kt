package com.example.testprojects.ui.fragments

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.*
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.testprojects.R
import com.example.testprojects.databinding.FragmentQuizBinding
import com.example.testprojects.ui.viewmodel.QuizViewModel
import com.google.android.material.color.MaterialColors

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        observeQuestionIndex()
    }

    private fun setupListeners() {
        binding.btnSkip.setOnClickListener {
            viewModel.skipQuestion()
            goToNext()
        }
    }

    private fun showOptions(
        options: List<String>,
        correctAnswer: Int,
        onOptionSelected: () -> Unit = {}
    ) {
        binding.layoutOptions.removeAllViews()
        val context = requireContext()

        // Resolve theme-aware text color
        val resolvedTextColor = MaterialColors.getColor(
            context,
            com.google.android.material.R.attr.colorOnSurface,
            Color.WHITE
        )

        options.forEachIndexed { index, optionText ->
            val optionView = TextView(context).apply {
                text = optionText
                textSize = 16f
                setTextColor(resolvedTextColor)
                background = ContextCompat.getDrawable(context, R.drawable.bg_skip)
                setPadding(40, 24, 40, 24)
                gravity = Gravity.CENTER
                isClickable = true
                isFocusable = true

                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topMargin = 40
                }

                setOnClickListener {
                    // Disable all options
                    for (i in 0 until binding.layoutOptions.childCount) {
                        binding.layoutOptions.getChildAt(i).isClickable = false
                    }

                    // Show correct and incorrect answers
                    for (i in 0 until binding.layoutOptions.childCount) {
                        val child = binding.layoutOptions.getChildAt(i) as TextView
                        val isCorrect = i == correctAnswer
                        val isSelected = child.text == optionText

                        child.background = when {
                            isSelected && isCorrect -> ContextCompat.getDrawable(context, R.drawable.bg_option_correct)
                            isSelected && !isCorrect -> ContextCompat.getDrawable(context, R.drawable.bg_option_wrong)
                            !isSelected && isCorrect -> ContextCompat.getDrawable(context, R.drawable.bg_option_correct)
                            else -> ContextCompat.getDrawable(context, R.drawable.bg_option)
                        }
                    }

                    viewModel.submitAnswer(optionText)
                    onOptionSelected()

                    handler.postDelayed({
                        goToNext()
                    }, 2000)
                }
            }

            binding.layoutOptions.addView(optionView)
        }
    }

    private fun observeQuestionIndex() {
        viewModel.currentIndex.observe(viewLifecycleOwner) { index ->
            val questions = viewModel.questions.value ?: return@observe

            if (index >= questions.size) {
                findNavController().navigate(R.id.action_quizFragment_to_resultFragment)
                return@observe
            }

            val current = questions[index]
            updateQuestionUI(index, questions.size, current.options, current.correctOptionIndex)
            binding.tvQuestion.text = current.question
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestionUI(index: Int, total: Int, options: List<String>, answer: Int) {
        val currentIndex = viewModel.currentIndex.value ?: 0
        val totalQuestions = viewModel.questions.value?.size ?: 0
        updateLeafProgress(currentIndex, totalQuestions)

        binding.tvQuestionCount.text = "Question ${index + 1} of $total"
        showOptions(options, answer)

        binding.progressBarHorizontal.progress = ((index + 1) * 100) / total

        val streak = viewModel.streak
        if (currentIndex > 0 && streak > 1) {
            binding.tvStreakMessage.apply {
                alpha = 1f
                text = "$streak questions streak achieved!!"
                visibility = View.VISIBLE

                animate()
                    .alpha(0f)
                    .setStartDelay(1500)
                    .setDuration(300)
                    .withEndAction {
                        visibility = View.GONE
                        alpha = 1f
                    }
                    .start()
            }
        } else {
            binding.tvStreakMessage.clearAnimation()
            binding.tvStreakMessage.visibility = View.GONE
            binding.tvStreakMessage.alpha = 1f
        }
    }

    private fun updateLeafProgress(currentIndex: Int, totalQuestions: Int) {
        val totalLeaves = 5
        val currentQuestion = currentIndex + 1

        val progressRatio = currentQuestion.toFloat() / totalQuestions
        val redLeafCount = (progressRatio * totalLeaves).toInt().coerceAtMost(totalLeaves)

        val leafViews = listOf(
            binding.leaf1,
            binding.leaf2,
            binding.leaf3,
            binding.leaf4,
            binding.leaf5
        )

        leafViews.forEachIndexed { index, imageView ->
            val colorRes = if (index < redLeafCount) {
                android.R.color.holo_red_dark
            } else {
                android.R.color.white
            }

            imageView.setColorFilter(
                ContextCompat.getColor(requireContext(), colorRes),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun goToNext() {
        viewModel.goToNextQuestion()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }
}