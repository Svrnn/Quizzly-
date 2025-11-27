package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityKategoriBinding

class KategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKategoriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Aksi Klik Kategori SEJARAH (BARU)
        binding.btnSejarah.setOnClickListener {
            // Pindah ke halaman Kuis
            val intent = Intent(this, QuizGameActivity::class.java)
            startActivity(intent)
        }

        // 2. Navigasi Bawah: Tombol Beranda -> Balik ke HomeActivity
        binding.btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 3. Navigasi Bawah: Tombol Profil -> Pindah ke ProfileActivity
        binding.btnNavProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // 4. Tombol Kategori (Opsional, diam saja karena sedang di halaman ini)
        binding.btnNavCategory.setOnClickListener {
            // Refresh halaman atau biarkan kosong
        }
    }
}