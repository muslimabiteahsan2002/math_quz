package com.muslima.math_quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.muslima.math_quiz.databinding.ActivityGameBinding
import java.util.Random

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private lateinit var timeText: TextView
    private lateinit var totalQuestionText: TextView
    private lateinit var questionText: TextView
    private lateinit var answer: TextView
    private lateinit var button0: AppCompatButton
    private lateinit var button1: AppCompatButton
    private lateinit var button2: AppCompatButton
    private lateinit var button3: AppCompatButton
    private lateinit var countDownTimer: CountDownTimer
    private var a = 0
    private var b = 0
    private var random = Random()
    private var indexOfCorrectAnswer = 0
    private var points = 0
    private var totalQuestions = 0
    private var answers = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timeText = binding.timeText
        totalQuestionText = binding.totalQuestionText
        questionText = binding.questionText
        answer = binding.answer
        button0 = binding.button0
        button1 = binding.button1
        button2 = binding.button2
        button3 = binding.button3
        start()
        optionSelect()
    }
    @SuppressLint("SetTextI18n")
    private fun nextQuestion() {
        a = random.nextInt(10)
        b = random.nextInt(10)
        questionText.text = "$a + $b = ?"
        indexOfCorrectAnswer = random.nextInt(4)
        answers.clear()
        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {
                answers.add(a+b)
            } else {
                var wrongAnswer = random.nextInt(20)
                while (wrongAnswer == a+b) {
                    wrongAnswer = random.nextInt(20)
                }
                answers.add(wrongAnswer)
            }
        }
        button0.text = "${answers[0]}"
        button1.text = "${answers[1]}"
        button2.text = "${answers[2]}"
        button3.text = "${answers[3]}"
    }
    @SuppressLint("SetTextI18n")
    private fun optionSelect() {
        button0.setOnClickListener {
            totalQuestions++
            if (indexOfCorrectAnswer.toString() == it.tag.toString()) {
                points++
                answer.text = "right"
            } else {
                answer.text = "wrong"
            }
            totalQuestionText.text = "$points/$totalQuestions"
            nextQuestion()
        }
        button1.setOnClickListener {
            totalQuestions++
            if (indexOfCorrectAnswer.toString() == it.tag.toString()) {
                points++
                answer.text = "right"
            } else {
                answer.text = "wrong"
            }
            totalQuestionText.text = "$points/$totalQuestions"
            nextQuestion()
        }
        button2.setOnClickListener {
            totalQuestions++
            if (indexOfCorrectAnswer.toString() == it.tag.toString()) {
                points++
                answer.text = "right"
            } else {
                answer.text = "wrong"
            }
            totalQuestionText.text = "$points/$totalQuestions"
            nextQuestion()
        }
        button3.setOnClickListener {
            totalQuestions++
            if (indexOfCorrectAnswer.toString() == it.tag.toString()) {
                points++
                answer.text = "right"
            } else {
                answer.text = "wrong"
            }
            totalQuestionText.text = "$points/$totalQuestions"
            nextQuestion()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun start() {
        nextQuestion()
        countDownTimer = object : CountDownTimer(60000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "${millisUntilFinished/1000} second"
            }

            override fun onFinish() {
                timeText.text = "time up"
                totalQuestionText.text = "$points/$totalQuestions"
                val result = Intent(this@GameActivity, ResultActivity::class.java)
                result.putExtra(pointKey, points)
                result.putExtra(totalQuestionKey, totalQuestions)
                startActivity(result)
            }
        }.start()
    }
}