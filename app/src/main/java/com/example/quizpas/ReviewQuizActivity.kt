package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityReviewQuizBinding

class ReviewQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol X (Close) -> Tutup
        binding.btnClose.setOnClickListener {
            // Bisa diarahkan ke Home atau sekedar finish
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        // 2. Tombol Kembali -> Balik ke AddQuestion
        binding.btnPrevStep.setOnClickListener {
            finish()
        }

        // 3. Tombol Publikasikan Kuis
        binding.btnPublish.setOnClickListener {
            // Simpan ke database (Simulasi)
            Toast.makeText(this, "Kuis Berhasil Dipublikasikan!", Toast.LENGTH_LONG).show()

            // Kembali ke Dashboard (Home) dan hapus history pembuatan kuis
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}