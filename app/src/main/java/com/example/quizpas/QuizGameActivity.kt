package com.example.quizpas // Sesuaikan nama package jika berbeda

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizGameActivity : AppCompatActivity() {

    // Deklarasi Variabel UI
    private lateinit var tvProgress: TextView      // Di XML id-nya tvProgress
    private lateinit var progressBar: ProgressBar  // Di XML id-nya progressBar
    private lateinit var tvTimer: TextView
    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbOption1: RadioButton
    private lateinit var rbOption2: RadioButton
    private lateinit var rbOption3: RadioButton
    private lateinit var rbOption4: RadioButton
    private lateinit var btnNext: Button
    private lateinit var btnClose: ImageView

    // Variabel Logika Game
    private var currentQuestionIndex = 0
    private var score = 0
    private var countDownTimer: CountDownTimer? = null

    // Model Data Soal
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswerIndex: Int // 0, 1, 2, atau 3
    )

    // Daftar Soal
    private val questionList = listOf(
        Question(
            "Siapa nama pendiri Ngawi Empire?",
            listOf("Rusdi", "Satria Lionel", "Susilo Bambang Yudhoyono", "Arnolt"),
            0 // Jawaban: Rusdi
        ),
        Question(
            "Ibu kota Indonesia yang baru bernama?",
            listOf("Jakarta", "Nusantara", "Bandung", "Surabaya"),
            1 // Jawaban: Nusantara
        ),
        Question(
            "2 + 2 x 2 = ?",
            listOf("8", "6", "4", "10"),
            1 // Jawaban: 6
        ),
        Question(
            "Apa nama alat musik yang dimainkan dengan cara dipetik?",
            listOf("Piano", "Gitar", "Suling", "Drum"),
            1 // Jawaban: Gitar
        ),
        Question(
            "Siapakah presiden pertama Indonesia?",
            listOf("Soeharto", "BJ Habibie", "Soekarno", "Jokowi"),
            2 // Jawaban: Soekarno
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        // 1. Inisialisasi View (SESUAI ID DI XML KAMU)
        tvProgress = findViewById(R.id.tvProgress)
        progressBar = findViewById(R.id.progressBar)
        tvTimer = findViewById(R.id.tvTimer)
        tvQuestion = findViewById(R.id.tvQuestion)
        rgOptions = findViewById(R.id.rgOptions)
        rbOption1 = findViewById(R.id.rbOption1)
        rbOption2 = findViewById(R.id.rbOption2)
        rbOption3 = findViewById(R.id.rbOption3)
        rbOption4 = findViewById(R.id.rbOption4)
        btnNext = findViewById(R.id.btnNext)
        btnClose = findViewById(R.id.btnClose)

        // 2. Setup Awal
        setupQuiz()

        // 3. Aksi Tombol Selanjutnya
        btnNext.setOnClickListener {
            checkAnswerAndNext()
        }

        // 4. Aksi Tombol Close
        btnClose.setOnClickListener {
            finish() // Kembali ke menu sebelumnya
        }
    }

    private fun setupQuiz() {
        if (currentQuestionIndex < questionList.size) {
            // Reset Timer
            countDownTimer?.cancel()

            // Ambil data soal
            val currentQuestion = questionList[currentQuestionIndex]

            // Update Teks Pertanyaan & Pilihan
            tvQuestion.text = currentQuestion.questionText
            rbOption1.text = currentQuestion.options[0]
            rbOption2.text = currentQuestion.options[1]
            rbOption3.text = currentQuestion.options[2]
            rbOption4.text = currentQuestion.options[3]

            // Update Teks "Soal 1/10"
            val progressText = "Soal ${currentQuestionIndex + 1}/${questionList.size}"
            tvProgress.text = progressText

            // Update Garis Progress Bar
            progressBar.max = questionList.size
            progressBar.progress = currentQuestionIndex + 1

            // Reset Pilihan RadioButton
            rgOptions.clearCheck()

            // Mulai Timer
            startTimer()

        } else {
            // JIKA SOAL HABIS
            finishQuiz()
        }
    }

    private fun startTimer() {
        // 30 Detik
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@QuizGameActivity, "Waktu Habis!", Toast.LENGTH_SHORT).show()
                checkAnswerAndNext(isTimeOut = true) // Pindah otomatis kalau waktu habis
            }
        }.start()
    }

    private fun checkAnswerAndNext(isTimeOut: Boolean = false) {
        if (isTimeOut) {
            // Kalau waktu habis, langsung lanjut tanpa nambah skor
            currentQuestionIndex++
            setupQuiz()
            return
        }

        if (rgOptions.checkedRadioButtonId != -1) {
            // Cek Jawaban User
            var selectedIndex = -1
            when (rgOptions.checkedRadioButtonId) {
                R.id.rbOption1 -> selectedIndex = 0
                R.id.rbOption2 -> selectedIndex = 1
                R.id.rbOption3 -> selectedIndex = 2
                R.id.rbOption4 -> selectedIndex = 3
            }

            if (selectedIndex == questionList[currentQuestionIndex].correctAnswerIndex) {
                score += 10 // Tambah skor
            }

            currentQuestionIndex++
            setupQuiz()

        } else {
            Toast.makeText(this, "Pilih jawaban dulu!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun finishQuiz() {
        countDownTimer?.cancel()

        // Pindah ke Halaman Hasil (Sementara pakai Toast dulu kalau belum ada ResultActivity)
        // Kalau sudah ada ResultActivity, uncomment kode di bawah ini:

        /*
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("SKOR_AKHIR", score)
        startActivity(intent)
        finish()
        */

        // Sementara tampilkan skor di layar:
        Toast.makeText(this, "Kuis Selesai! Skor kamu: $score", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}