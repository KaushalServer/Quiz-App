package com.example.myapplication

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionList = arrayOf(
        "What is built-in Databse in Android Studio?",
        "What is the full form of APK in Android Development?",
        "In which year, first android was released by Google?"
    )

    private val options  = arrayOf(arrayOf("MySql", "SQLite", "Firebase"),
        arrayOf("Application Programming Interface","Android Programming Interface", "Android Package Information"),
        arrayOf("2010","2006","2008"))

    private val correctAns = arrayOf(1,0,2)
    private var currentQuesIndex = 0
    private var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding
            .root)

        displayQuest()

        binding.option1.setOnClickListener {
                checkAns(0)
        }
        binding.option2.setOnClickListener {
                checkAns(1)
        }
        binding.option3.setOnClickListener {
                checkAns(2)
        }
        binding.restartBtn.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctBtnColors(btnIndex: Int){
        when(btnIndex){
            0 -> binding.option1.setBackgroundColor(Color.GREEN)
            1 -> binding.option2.setBackgroundColor(Color.GREEN)
            2 -> binding.option3.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongBtnColors(btnIndex: Int){
        when(btnIndex){
            0 -> binding.option1.setBackgroundColor(Color.RED)
            1 -> binding.option2.setBackgroundColor(Color.RED)
            2 -> binding.option3.setBackgroundColor(Color.RED)
        }
    }

    private fun resetBtnColors(){
        binding.option1.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this, "Your score $score out of ${questionList.size}", Toast.LENGTH_SHORT).show()
        binding.restartBtn.isEnabled = true
    }

    private fun displayQuest(){
        binding.quesText.text = questionList[currentQuesIndex]
        binding.option1.text = options[currentQuesIndex][0]
        binding.option2.text = options[currentQuesIndex][1]
        binding.option3.text = options[currentQuesIndex][2]
        resetBtnColors()
    }

    private fun checkAns(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAns[currentQuesIndex]

        if(selectedAnswerIndex == correctAnswerIndex){
            score++
            correctBtnColors(selectedAnswerIndex)
        } else {
            wrongBtnColors(selectedAnswerIndex)
            correctBtnColors(correctAnswerIndex)
        }

        if(currentQuesIndex < questionList.size - 1){
            currentQuesIndex++
            binding.quesText.postDelayed({displayQuest()}, 1000)
        } else {
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuesIndex = 0
        score = 0
        displayQuest()
        binding.restartBtn.isEnabled = false
    }
}