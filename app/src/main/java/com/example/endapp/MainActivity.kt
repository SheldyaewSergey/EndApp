package com.example.endapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.endapp.databinding.ActivityLearnWordBinding
import com.example.endapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityLearnWordBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException("Binding for ActivityLearnWordBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityLearnWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val trainer = LearnWordTrainer()
        showNextQuestion(trainer)

        with(binding){
            btnContinue.setOnClickListener{
                layoutResult.isVisible = false
                markAnswerNeutral(layoutAnswer1, tvVarNum1, tvVariantValue1)
                markAnswerNeutral(layoutAnswer2, tvVarNum2, tvVariantValue2)
                markAnswerNeutral(layoutAnswer3, tvVarNum3, tvVariantValue3)
                markAnswerNeutral(layoutAnswer4, tvVarNum4, tvVariantValue4)
                showNextQuestion(trainer)
            }
            btnSkip.setOnClickListener{showNextQuestion(trainer)}
        }
    }

    private fun showNextQuestion(trainer: LearnWordTrainer) {
        val firstQuestion: Question? = trainer.getNextQuestin()
        with(binding){
            if (firstQuestion == null || firstQuestion.variants.size < NUMBER_OF_ANSWERS) {
                tvQuestionWord.isVisible = false
                layoutVariants.isVisible = false
                btnSkip.text = "Complete"
            } else {
                btnSkip.isVisible = true
                tvQuestionWord.isVisible = true
                tvQuestionWord.text = firstQuestion.correctAnswer.original

                tvVarNum1.text = firstQuestion.variants[0].translated
                tvVarNum2.text = firstQuestion.variants[1].translated
                tvVarNum3.text = firstQuestion.variants[2].translated
                tvVarNum4.text = firstQuestion.variants[3].translated

                layoutAnswer1.setOnClickListener {
                    if(trainer.checkAnswer(0)) {
                        markAnswerCorrect(layoutAnswer1, tvVarNum1, tvVariantValue1)
                        showResult(true)
                    } else {
                        markAnswerIncorrect(layoutAnswer1, tvVarNum1, tvVariantValue1)
                        showResult(false)
                    }
                }
                layoutAnswer2.setOnClickListener {
                    if(trainer.checkAnswer(1)) {
                        markAnswerCorrect(layoutAnswer2, tvVarNum2, tvVariantValue2)
                        showResult(true)
                    } else {
                        markAnswerIncorrect(layoutAnswer2, tvVarNum2, tvVariantValue2)
                        showResult(false)
                    }
                }
                layoutAnswer3.setOnClickListener {
                    if(trainer.checkAnswer(2)) {
                        markAnswerCorrect(layoutAnswer3, tvVarNum3, tvVariantValue3)
                        showResult(true)
                    } else {
                        markAnswerIncorrect(layoutAnswer3, tvVarNum3, tvVariantValue3)
                        showResult(false)
                    }
                }
                layoutAnswer4.setOnClickListener {
                    if(trainer.checkAnswer(3)) {
                        markAnswerCorrect(layoutAnswer4, tvVarNum4, tvVariantValue4)
                        showResult(true)
                    } else {
                        markAnswerIncorrect(layoutAnswer4, tvVarNum4, tvVariantValue4)
                        showResult(false)
                    }
                }
            }
        }

    }

    private fun markAnswerNeutral(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {

                layoutAnswer.background = ContextCompat.getDrawable(
                    this@MainActivity,
                    R.drawable.shape_rounded_selector
                )


                tvVariantNumber.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.textVariantsColor
                    )
                )



        tvVariantValue.apply {
                    background = ContextCompat.getDrawable(
                        this@MainActivity,
                        R.drawable.shape_rounded_variants,
                    )
                    setTextColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.textVariantsColor,
                        )
                    )
                }


    }

    private fun markAnswerCorrect(
        layoutAnswer: LinearLayout,
        tvVarNum: TextView,
        tvVariantValue: TextView, ) {

        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_correct
        )

        tvVarNum.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_correct
        )

        tvVarNum.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.correctAnswerColor
            )
        )

    }

    private fun markAnswerIncorrect(
        layoutAnswer: LinearLayout,
        tvVariantNumber: TextView,
        tvVariantValue: TextView,
    ) {

        layoutAnswer.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_containers_incorrect
        )

        tvVariantNumber.background = ContextCompat.getDrawable(
            this@MainActivity,
            R.drawable.shape_rounded_variants_incorrect
        )

        tvVariantNumber.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.white
            )
        )

        tvVariantValue.setTextColor(
            ContextCompat.getColor(
                this@MainActivity,
                R.color.InCorrectAnswerColor
            )
        )

    }

    private fun showResult(isCorrect: Boolean){
        val color: Int
        val messageText: String
        val resIconResource: Int
        if (isCorrect) {
            color = ContextCompat.getColor(this, R.color.correctAnswerColor)
            resIconResource = R.drawable.ic_correct
            messageText = "Correct!"
        }else {
            color = ContextCompat.getColor(this, R.color.InCorrectAnswerColor)
            resIconResource = R.drawable.ic_wrong
            messageText = "Incorrect!"
        }

        with(binding) {
            btnSkip.isVisible = false
            layoutResult.isVisible = true
            btnContinue.setTextColor(color)
            layoutResult.setBackgroundColor(color)
            tvResult.text = messageText
            ivCorrectResultIcon.setImageResource(resIconResource)
        }
    }
}