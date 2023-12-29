package com.muslima.math_quiz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.muslima.math_quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var finalScoreText: TextView
    private lateinit var playAgainBtn: AppCompatButton
    private var points = 0
    private var totalQuestions = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        finalScoreText = binding.finalScoreText
        playAgainBtn = binding.playAgainBtn
        points = intent.getIntExtra(pointKey, 0)
        totalQuestions = intent.getIntExtra(totalQuestionKey, 0)
        finalScoreText.text = "$points/$totalQuestions"
        playAgainBtn.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }
}