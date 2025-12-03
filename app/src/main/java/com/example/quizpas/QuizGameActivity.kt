package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class QuizGameActivity : AppCompatActivity() {

    // Deklarasi Variabel UI
    private lateinit var tvProgress: TextView
    private lateinit var progressBar: ProgressBar
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
    private var score = 0              // Menampung total skor
    private var correctAnswers = 0     // Menampung jumlah jawaban benar
    private var countDownTimer: CountDownTimer? = null

    // Model Data Soal
    data class Question(
        val questionText: String,
        val options: List<String>,
        val correctAnswerIndex: Int // 0, 1, 2, atau 3
    )

    // --- DAFTAR SOAL MEME NASIHUY ---
    // Total 5 Soal (Nilai 20 poin per soal)
    private val questionList = listOf(
        Question(
            "Siapakah pemimpin tertinggi dari Ngawi Empire?",
            listOf("Rusdi", "Ambatron", "Mas Fuad", "Mr. Ironi"),
            0 // Jawaban Benar: Rusdi
        ),
        Question(
            "Siapakah cyborg dari tahun 2050 yang menjadi musuh bebuyutan Ngawi?",
            listOf("Megatron", "Ambatron", "Optimus Prime", "Terminator"),
            1 // Jawaban Benar: Ambatron
        ),
        Question(
            "Apa pekerjaan asli Mas Rusdi sebelum menjadi raja?",
            listOf("Presiden", "Tukang Cukur", "Dokter Gigi", "Pilot"),
            1 // Jawaban Benar: Tukang Cukur (Barbershop)
        ),
        Question(
            "Dimana tempat persembunyian favorit Mas Fuad?",
            listOf("Di atas genteng", "Di dalam kulkas", "Di bawah kasur", "Di dalam gua"),
            1 // Jawaban Benar: Di dalam kulkas
        ),
        Question(
            "Apa slogan legendaris dari kapybara 'Masbro'?",
            listOf("Pinjam dulu seratus", "Gas ngeng", "Santai dulu gak sih", "Gak bahaya ta"),
            2 // Jawaban Benar: Santai dulu gak sih
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_game)

        // 1. Inisialisasi View
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
            finish()
        }
    }

    private fun setupQuiz() {
        if (currentQuestionIndex < questionList.size) {
            // Reset Timer
            countDownTimer?.cancel()

            // Ambil data soal
            val currentQuestion = questionList[currentQuestionIndex]

            // Update UI
            tvQuestion.text = currentQuestion.questionText
            rbOption1.text = currentQuestion.options[0]
            rbOption2.text = currentQuestion.options[1]
            rbOption3.text = currentQuestion.options[2]
            rbOption4.text = currentQuestion.options[3]

            // Update Progress Bar
            val progressText = "Soal ${currentQuestionIndex + 1}/${questionList.size}"
            tvProgress.text = progressText
            progressBar.max = questionList.size
            progressBar.progress = currentQuestionIndex + 1

            // --- LOGIKA UBAH TOMBOL JADI "SUBMIT" DI SOAL TERAKHIR ---
            if (currentQuestionIndex == questionList.size - 1) {
                btnNext.text = "Submit"
            } else {
                btnNext.text = "Selanjutnya"
            }

            // Reset Pilihan RadioButton
            rgOptions.clearCheck()

            // Mulai Timer
            startTimer()
        } else {
            finishQuiz()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = (millisUntilFinished / 1000).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@QuizGameActivity, "Waktu Habis!", Toast.LENGTH_SHORT).show()
                checkAnswerAndNext(isTimeOut = true)
            }
        }.start()
    }

    private fun checkAnswerAndNext(isTimeOut: Boolean = false) {
        if (isTimeOut) {
            // Jika waktu habis, langsung lanjut tanpa nambah skor
            currentQuestionIndex++
            setupQuiz()
            return
        }

        if (rgOptions.checkedRadioButtonId != -1) {
            var selectedIndex = -1
            when (rgOptions.checkedRadioButtonId) {
                R.id.rbOption1 -> selectedIndex = 0
                R.id.rbOption2 -> selectedIndex = 1
                R.id.rbOption3 -> selectedIndex = 2
                R.id.rbOption4 -> selectedIndex = 3
            }

            // --- HITUNG SKOR (20 POIN PER SOAL) ---
            if (selectedIndex == questionList[currentQuestionIndex].correctAnswerIndex) {
                score += 20
                correctAnswers++ // Tambah jumlah benar
            }

            currentQuestionIndex++
            setupQuiz()
        } else {
            Toast.makeText(this, "Pilih jawaban dulu!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun finishQuiz() {
        countDownTimer?.cancel()

        // --- KIRIM DATA LENGKAP KE HALAMAN HASIL ---
        val intent = Intent(this, QuizResultActivity::class.java)
        intent.putExtra("SKOR_AKHIR", score)       // Kirim Total Skor
        intent.putExtra("JUMLAH_BENAR", correctAnswers) // Kirim Jumlah Benar
        intent.putExtra("TOTAL_SOAL", questionList.size) // Kirim Total Soal

        startActivity(intent)
        finish() // Tutup halaman kuis
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}