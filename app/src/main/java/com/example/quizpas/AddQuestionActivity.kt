package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityAddQuestionBinding

class AddQuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Close
        binding.btnClose.setOnClickListener { finish() }

        // Tombol Simpan Pertanyaan
        binding.btnSaveQuestion.setOnClickListener {
            if (binding.etQuestion.text.isEmpty()) {
                Toast.makeText(this, "Isi pertanyaan dulu!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pertanyaan disimpan!", Toast.LENGTH_SHORT).show()
                // Di sini logika simpan ke database atau array sementara
                // Kosongkan input setelah simpan
                binding.etQuestion.text.clear()
                binding.etAnswer1.text.clear()
                binding.etAnswer2.text.clear()
                binding.etAnswer3.text.clear()
                binding.etAnswer4.text.clear()
            }
        }

        // Di dalam AddQuestionActivity.kt
        binding.btnNextStep.setOnClickListener {
            // Pindah ke halaman Review & Publikasi
            val intent = Intent(this, ReviewQuizActivity::class.java)
            startActivity(intent)
        }

        // Tombol Tambah Pilihan (Hanya Hiasan/Toast karena layout kita statis 4 kolom)
        binding.btnAddOption.setOnClickListener {
            Toast.makeText(this, "Maksimal 4 pilihan jawaban", Toast.LENGTH_SHORT).show()
        }
    }
}