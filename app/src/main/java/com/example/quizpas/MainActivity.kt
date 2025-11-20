package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Menggunakan Binding untuk layout activity_main.xml (Halaman Profil)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Logika Tombol "Masuk" (Biru)
        // Saat diklik, pindah ke LoginActivity
        binding.btnToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // 2. Logika Tombol "Buat Akun" (Abu-abu)
        // Saat diklik, pindah ke RegisterActivity
        binding.btnToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}