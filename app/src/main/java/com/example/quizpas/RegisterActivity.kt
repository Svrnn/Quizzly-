package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- LOGIKA TOMBOL DAFTAR ---
        binding.btnRegister.setOnClickListener {
            // 1. Ambil teks dari kolom input
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            val confirmPassword = binding.etConfirmPassword.text.toString().trim()

            // 2. Validasi: Cek apakah ada yang kosong
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Mohon isi semua data!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Validasi: Cek apakah password cocok
            if (password != confirmPassword) {
                Toast.makeText(this, "Kata sandi tidak cocok!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4. JIKA SUKSES (Simulasi)
            Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()

            // Pindah ke Halaman Utama (HomeActivity)
            val intent = Intent(this, HomeActivity::class.java)

            // MENGIRIM NAMA ke HomeActivity agar sapaannya berubah
            intent.putExtra("USER_NAME", name)

            startActivity(intent)

            // Menutup semua activity sebelumnya agar user tidak bisa balik ke menu daftar saat tekan Back
            finishAffinity()
        }

        // --- LOGIKA LINK "MASUK" (Sudah punya akun?) ---
        binding.tvLoginLink.setOnClickListener {
            // Kembali ke halaman Login
            finish()
        }
    }
}