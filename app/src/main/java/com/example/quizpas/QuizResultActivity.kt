package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityQuizResultBinding

class QuizResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Close (X) -> Ke Home
        binding.btnClose.setOnClickListener {
            goToHome()
        }

        // 2. Tombol Ulangi Kuis -> Ke Kategori
        binding.btnRepeatQuiz.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 3. Tombol Kembali ke Beranda -> Ke Home
        binding.btnBackHome.setOnClickListener {
            goToHome()
        }

        // 4. Navigasi Bawah (Optional, biar tombolnya jalan)
        binding.btnNavHome.setOnClickListener { goToHome() }
        binding.btnNavCategory.setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }
        binding.btnNavProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    // Fungsi pindah ke Home dan hapus history back
    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}