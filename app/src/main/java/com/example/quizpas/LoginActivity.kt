package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- LOGIKA TOMBOL MASUK ---
        binding.btnLogin.setOnClickListener {
            // 1. Ambil teks dari input
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // 2. Validasi: Cek jika kosong
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Mohon isi email dan kata sandi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Simulasi Login Berhasil (Karena belum ada database)
            // Di sini kamu bisa tambahkan logika cek password jika mau
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_SHORT).show()

            // 4. Pindah ke HomeActivity
            val intent = Intent(this, HomeActivity::class.java)

            // Kirim Nama ke Home (Ambil dari email atau hardcode "Arya" dulu)
            val userName = if (email.contains("@")) email.substringBefore("@") else email
            intent.putExtra("USER_NAME", userName)

            startActivity(intent)
            finish() // Tutup Login agar tidak bisa balik lagi
        }

        // --- LOGIKA LINK DAFTAR (Belum punya akun?) ---
        binding.tvRegisterLink.setOnClickListener {
            // Pindah ke halaman Register
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}