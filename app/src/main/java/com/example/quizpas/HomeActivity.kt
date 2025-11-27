package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Menangkap Nama User dari Login
        val userName = intent.getStringExtra("USER_NAME")
        if (userName != null) {
            binding.tvWelcomeName.text = "Selamat datang, $userName!"
        }

        // 2. TOMBOL "MULAI KUIS BARU" -> Pindah ke KategoriActivity
        binding.btnStartNewQuiz.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            startActivity(intent)
        }

        // 3. TOMBOL TAMBAH (+) -> Pindah ke Halaman Buat Kuis
        binding.ivAdd.setOnClickListener {
            val intent = Intent(this, CreateQuizActivity::class.java)
            startActivity(intent)
        }

        // 4. KLIK FOTO PROFIL -> Pindah ke ProfileActivity
        // (Pastikan ID gambar profil di XML kamu adalah @+id/ivProfilePicture)
        binding.ivProfilePicture.setOnClickListener {
            // Kita arahkan ke ProfileActivity dulu (Halaman Statistik)
            // Di sana nanti ada tombol "Edit Profil" jika ingin mengedit
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Catatan: Jika kamu ingin langsung lompat ke form edit (tanpa lihat statistik dulu),
        // ganti ProfileActivity::class.java menjadi EditProfileActivity::class.java
    }
}