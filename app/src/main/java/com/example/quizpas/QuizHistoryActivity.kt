package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityQuizHistoryBinding

class QuizHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Kembali
        binding.btnBack.setOnClickListener {
            finish() // Kembali ke halaman Profil
        }

        // 2. Navigasi Bawah
        // Beranda -> Home
        binding.btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Kategori -> Kategori
        binding.btnNavCategory.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            startActivity(intent)
            finish()
        }
        // Profil -> Balik ke ProfileActivity
        binding.btnNavProfile.setOnClickListener {
            // Karena ini sub-menu profil, kita bisa finish saja atau arahkan ke ProfileActivity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}