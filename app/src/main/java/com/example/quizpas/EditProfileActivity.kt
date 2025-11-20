package com.example.quizpas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Tombol Kembali (Panah Kiri)
        binding.btnBack.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }

        // 2. Tombol Simpan
        binding.btnSave.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()

            // Logika simpan data (Simulasi)
            Toast.makeText(this, "Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()

            // Tutup halaman edit setelah simpan
            finish()
        }

        // Opsional: Set data awal di kolom input
        // binding.etUsername.setText("Karamuy")
        // binding.etEmail.setText("davina@example.com")
    }
}