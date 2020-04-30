package com.almaas.quizonline

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import com.almaas.quizonline.Model.Helper.gson
import com.almaas.quizonline.Model.Question
import com.google.firebase.database.*

class QuestionActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var questionTxt: TextView
    private lateinit var textView4: TextView
    private lateinit var questionImg: ImageView
    private lateinit var b1: Button
    private lateinit var b2: Button
    private lateinit var b3: Button
    private lateinit var b4: Button

    var correct: Int = 0
    var wrong: Int = 0
    var total: Int = 0
    var nilai: Int = 0
   //variable untuk menyimpan jumlah soal yang dijawab
    var computerCount: Int = 0
    //question berbentuk list
    var questions = listOf<Question>()
    var subject = ""
    //menentukan limit soal
    val QUESTION_LIMIT = 20
    //untuk menentukan delay per soal
    val DELAY_TIME_IN_MS = 1000L

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        b1 = findViewById(R.id.OptionA)
        b2 = findViewById(R.id.OptionB)
        b3 = findViewById(R.id.OptionC)
        b4 = findViewById(R.id.OptionD)
        questionTxt = findViewById(R.id.Questions)
//        questionImg =
//            findViewById(R.id.question_image)
        textView4 = findViewById(R.id.time_question)
        val toolbar =
            findViewById<View>(R.id.toolbar) as Toolbar
        val i = intent
        subject = i.getStringExtra("subject")
        toolbar.title = subject
        retrieveQuestions()
        reverseTimer(2100, textView4)
    }

    override fun onRestart() {
        super.onRestart()
        retrieveQuestions()
        reverseTimer(2100, textView4)
    }

    // mengambil quesion firebabse
    fun retrieveQuestions() = FirebaseDatabase.getInstance().reference.child(subject)
        .addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {}

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            //melakukan random data
            override fun onDataChange(data: DataSnapshot) {
                val listData = data.value as List<Any>
                questions = listData.shuffled()
                    .filter { x -> x != null }
                    .map { gson.fromJson(gson.toJson(it), Question::class.java)}
                    .let {
                        if (it.size < QUESTION_LIMIT + 1) it
                        else it.subList(0, QUESTION_LIMIT + 1)
                    }
                updateQuestion()
            }
        })

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun updateQuestion() {
        if (computerCount == QUESTION_LIMIT || questions.size < computerCount) {
            nilai = correct * 100 / QUESTION_LIMIT;
            Toast.makeText(applicationContext, "Selesai", Toast.LENGTH_SHORT).show()
            val myIntent = Intent(this@QuestionActivity, ScoreActivity::class.java)
            myIntent.putExtra("total", QUESTION_LIMIT.toString())
            myIntent.putExtra("correct", correct.toString())
            myIntent.putExtra("incorrect", wrong.toString())
            myIntent.putExtra("hasil", nilai.toString())
            startActivity(myIntent)
        } else {
            val question = questions.get(computerCount)
            computerCount++
            questionTxt.setText("$computerCount. " + question.getQuestion())
            b1.setText(question.getOption1())
            b2.setText(question.getOption2())
            b3.setText(question.getOption3())
            b4.setText(question.getOption4())
            b1.setOnClickListener {
                if (b1.text.toString() == question.answer) {
                    Toast.makeText(applicationContext, "Benar", Toast.LENGTH_SHORT)
                        .show()
                    b1.setBackgroundColor(Color.GREEN)
                    correct++
                    val handler = Handler()
                    handler.postDelayed({
                        b1.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, DELAY_TIME_IN_MS)
                } else {
                    Toast.makeText(applicationContext, "Salah", Toast.LENGTH_SHORT)
                        .show()
                    wrong += 1
                    b1.setBackgroundColor(Color.RED)
                    if (b2.text.toString() == question.getAnswer()) {
                        b2.setBackgroundColor(Color.GREEN)
                    } else if (b3.text.toString() == question.getAnswer()) {
                        b3.setBackgroundColor(Color.GREEN)
                    } else if (b4.text.toString() == question.getAnswer()) {
                        b4.setBackgroundColor(Color.GREEN)
                    }
                    val handler = Handler()
                    handler.postDelayed({
                        b1.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b2.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b3.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b4.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, DELAY_TIME_IN_MS)
                }
            }
            b2.setOnClickListener {
                if (b2.text.toString() == question.answer) {
                    correct++
                    Toast.makeText(applicationContext, "Benar", Toast.LENGTH_SHORT)
                        .show()
                    b2.setBackgroundColor(Color.GREEN)
                    val handler = Handler()
                    handler.postDelayed({
                        b2.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, DELAY_TIME_IN_MS)
                } else {
                    wrong++
                    Toast.makeText(applicationContext, "Salah", Toast.LENGTH_SHORT)
                        .show()
                    b2.setBackgroundColor(Color.RED)
                    if (b1.text.toString() == question.getAnswer()) {
                        b1.setBackgroundColor(Color.GREEN)
                    } else if (b3.text.toString() == question.getAnswer()) {
                        b3.setBackgroundColor(Color.GREEN)
                    } else if (b4.text.toString() == question.getAnswer()) {
                        b4.setBackgroundColor(Color.GREEN)
                    }
                    val handler = Handler()
                    handler.postDelayed({
                        b1.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b2.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b3.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b4.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, DELAY_TIME_IN_MS)
                }
            }
            b3.setOnClickListener {
                if (b3.text.toString() == question.answer) {
                    correct++
                    Toast.makeText(applicationContext, "Benar", Toast.LENGTH_SHORT)
                        .show()
                    b3.setBackgroundColor(Color.GREEN)
                    val handler = Handler()
                    handler.postDelayed({
                        b3.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, DELAY_TIME_IN_MS)
                } else {
                    wrong++
                    Toast.makeText(applicationContext, "Salah", Toast.LENGTH_SHORT)
                        .show()
                    b3.setBackgroundColor(Color.RED)
                    if (b1.text.toString() == question.getAnswer()) {
                        b1.setBackgroundColor(Color.GREEN)
                    } else if (b2.text.toString() == question.getAnswer()) {
                        b2.setBackgroundColor(Color.GREEN)
                    } else if (b4.text.toString() == question.getAnswer()) {
                        b4.setBackgroundColor(Color.GREEN)
                    }
                    val handler = Handler()
                    handler.postDelayed({
                        b1.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b2.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b3.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b4.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, 1000)
                }
            }
            b4.setOnClickListener {
                if (b4.text.toString() == question.answer) {
                    correct++
                    Toast.makeText(applicationContext, "Benar", Toast.LENGTH_SHORT)
                        .show()
                    b4.setBackgroundColor(Color.GREEN)
                    val handler = Handler()
                    handler.postDelayed({
                        b4.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, DELAY_TIME_IN_MS)
                } else {
                    wrong++
                    Toast.makeText(applicationContext, "Salah", Toast.LENGTH_SHORT)
                        .show()
                    // make it red , and make right one Green
                    b4.setBackgroundColor(Color.RED)
                    if (b1.text.toString() == question.getAnswer()) {
                        b1.setBackgroundColor(Color.GREEN)
                    } else if (b2.text.toString() == question.getAnswer()) {
                        b2.setBackgroundColor(Color.GREEN)
                    } else if (b3.text.toString() == question.getAnswer()) {
                        b3.setBackgroundColor(Color.GREEN)
                    }
                    val handler = Handler()
                    handler.postDelayed({
                        b1.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b2.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b3.setBackgroundColor(Color.parseColor("#F7AA35"))
                        b4.setBackgroundColor(Color.parseColor("#F7AA35"))
                        updateQuestion()
                    }, DELAY_TIME_IN_MS)
                }
            }
        }
    }


    private fun reverseTimer(Seconds: Int, tv: TextView) {
        object : CountDownTimer((Seconds * 1000 + 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var seconds = (millisUntilFinished / 1000).toInt()
                val minutes = seconds / 60
                seconds %= 60
                tv.text = String.format(
                    "%02d",
                    minutes
                ) + ":" + String.format("%02d", seconds)
            }

            override fun onFinish() {
                tv.text = "Completed"
                val myIntent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                myIntent.putExtra("total", total.toString())
                myIntent.putExtra("correct", correct.toString())
                myIntent.putExtra("incorrect", wrong.toString())
                myIntent.putExtra("hasil", nilai.toString())
                startActivity(myIntent)
            }
        }.start()
    }
}
