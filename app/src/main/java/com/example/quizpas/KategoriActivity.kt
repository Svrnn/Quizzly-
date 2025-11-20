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

        // Navigasi Bawah: Tombol Beranda -> Balik ke HomeActivity
        binding.btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Navigasi Bawah: Tombol Profil -> Pindah ke ProfileActivity
        // (Pastikan ProfileActivity juga sudah dibuat nanti)
        binding.btnNavProfile.setOnClickListener {
            // val intent = Intent(this, ProfileActivity::class.java)
            // startActivity(intent)
        }
    }
}