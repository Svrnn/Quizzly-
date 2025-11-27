package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizLastActivity : AppCompatActivity() {

    // Deklarasi Variabel
    private lateinit var tvProgress: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvTimer: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnSubmit: Button // Tombol Submit
    private lateinit var btnClose: ImageView

    // Variabel Skor
    private var currentScore = 0
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_last) // Pastikan layoutnya benar

        // 1. Ambil Skor dari Activity Sebelumnya (jika ada)
        currentScore = intent.getIntExtra("SKOR_SEMENTARA", 0)

        // 2. Inisialisasi View
        tvProgress = findViewById(R.id.tvProgress) // Pastikan ID ini ada di XML
        progressBar = findViewById(R.id.progressBar)
        tvTimer = findViewById(R.id.tvTimer)
        rgOptions = findViewById(R.id.rgOptions)

        // Cek ID tombol di XML kamu, apakah 'btnSubmit' atau 'btnNext'
        // Jika error, ganti R.id.btnSubmit dengan R.id.btnNext
        btnSubmit = findViewById(R.id.btnNext)
        btnClose = findViewById(R.id.btnClose)

        // 3. Setup Tampilan Awal
        setupLastQuestion()

        // 4. Aksi Tombol Submit
        btnSubmit.setOnClickListener {
            finishQuizAndShowResult()
        }

        // 5. Aksi Tombol Close
        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun setupLastQuestion() {
        // Set Progress ke Maksimal (Contoh 10/10)
        tvProgress.text = "Soal 10/10"
        progressBar.progress = 10

        // Mulai Timer
        startTimer()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@QuizLastActivity, "Waktu Habis!", Toast.LENGTH_SHORT).show()
                finishQuizAndShowResult() // Otomatis submit saat waktu habis
            }
        }.start()
    }

    private fun finishQuizAndShowResult() {
        countDownTimer?.cancel()

        // Cek Jawaban
        val selectedId = rgOptions.checkedRadioButtonId
        if (selectedId != -1) {
            val selectedButton = findViewById<RadioButton>(selectedId)

            // KUNCI JAWABAN: Sesuaikan teks ini dengan jawaban benar di XML kamu
            // Misalnya jawaban benarnya "Sapphire Village"
            if (selectedButton.text.toString() == "Sapphire Village") {
                currentScore += 10
            }
        }

        // Pindah ke Halaman Result (Skor Akhir)
        // Pastikan kamu sudah punya 'ResultActivity'
        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra("SKOR_AKHIR", currentScore)
        startActivity(intent)
        finish() // Tutup halaman kuis
    }
}