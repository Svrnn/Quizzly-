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
    private lateinit var btnSubmit: Button
    private lateinit var btnClose: ImageView

    // Variabel Skor
    private var currentScore = 0
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_last)

        // 1. Ambil Skor dari Activity Sebelumnya (jika ada)
        // Default 0 jika tidak ada data kiriman
        currentScore = intent.getIntExtra("SKOR_SEMENTARA", 0)

        // 2. Inisialisasi View
        tvProgress = findViewById(R.id.tvProgress)
        progressBar = findViewById(R.id.progressBar)
        tvTimer = findViewById(R.id.tvTimer)
        rgOptions = findViewById(R.id.rgOptions)
        btnClose = findViewById(R.id.btnClose)

        // PENTING: Pastikan ID tombol di XML adalah 'btnNext'
        // Jika di XML namanya 'btnSubmit', ganti baris bawah ini jadi R.id.btnSubmit
        btnSubmit = findViewById(R.id.btnNext)

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

        // Mulai Timer 30 Detik
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

            // KUNCI JAWABAN
            // Pastikan tulisan di bawah SAMA PERSIS dengan teks di XML/Layout kamu
            if (selectedButton.text.toString() == "Sapphire Village") {
                currentScore += 10
            }
        }

        // Pindah ke Halaman Result (Skor Akhir)
        // Menggunakan QuizResultActivity (sesuai file yang sudah ada)
        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra("SKOR_AKHIR", currentScore)
        startActivity(intent)
        finish() // Tutup halaman kuis agar tidak bisa kembali
    }
}