package com.example.quizpas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityCreateQuizBinding

class CreateQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- 1. MENGISI DATA SPINNER (KATEGORI) ---

        // Daftar kategori yang kamu mau
        val categories = arrayOf("Pilih Kategori", "Sejarah", "Geografi", "Sains", "Matematika", "Literatur", "Seni")

        // Membuat Adapter untuk menghubungkan data ke Spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        // Memasang adapter ke Spinner
        binding.spCategory.adapter = adapter


        // --- 2. LOGIKA LAINNYA ---

        // Tombol Close (X)
        binding.btnClose.setOnClickListener {
            finish() // Kembali ke menu sebelumnya
        }

        // Tombol Lanjut
        binding.btnNext.setOnClickListener {
            val judul = binding.etQuizTitle.text.toString()
            val deskripsi = binding.etQuizDesc.text.toString()
            val kategori = binding.spCategory.selectedItem.toString()

            if (judul.isEmpty()) {
                Toast.makeText(this, "Judul kuis harus diisi!", Toast.LENGTH_SHORT).show()
            } else if (kategori == "Pilih Kategori") {
                Toast.makeText(this, "Silakan pilih kategori!", Toast.LENGTH_SHORT).show()
            } else {
                // Kalau sukses
                Toast.makeText(this, "Kuis '$judul' kategori $kategori dibuat!", Toast.LENGTH_SHORT).show()

                // Nanti di sini pindah ke halaman buat soal...
            }
        }
    }
}