package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Kembali (Panah Kiri) -> Balik ke Home
        binding.btnBack.setOnClickListener {
            finish()
        }

        // 2. Tombol Edit Profil -> Pindah ke EditProfileActivity
        binding.btnEditProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        // 3. Tombol Riwayat Kuis (INI YANG KEMARIN SALAH TEMPAT)
        // Sekarang sudah dikeluarkan agar bisa diklik kapan saja
        binding.btnQuizHistory.setOnClickListener {
            val intent = Intent(this, QuizHistoryActivity::class.java)
            startActivity(intent)
        }

        // 4. Navigasi Bawah: Beranda -> Pindah ke Home
        binding.btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        // 5. Navigasi Bawah: Kategori -> Pindah ke Kategori
        binding.btnNavCategory.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}