package com.example.quizpas

import android.content.Intent // PENTING: Jangan lupa import ini
import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityCreateQuizBinding

class CreateQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- 1. MENGISI DATA SPINNER ---
        val categories = arrayOf("Pilih Kategori", "Sejarah", "Geografi", "Sains", "Matematika", "Literatur", "Seni")

        // Pakai layout custom spinner_item agar teks berwarna HITAM
        val adapter = ArrayAdapter(this, R.layout.spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spCategory.adapter = adapter

        // --- 2. LOGIKA TOMBOL ---

        // Tombol Close
        binding.btnClose.setOnClickListener {
            finish()
        }

        // Tombol Lanjut
        binding.btnNext.setOnClickListener {
            val judul = binding.etQuizTitle.text.toString()
            val deskripsi = binding.etQuizDesc.text.toString()
            val kategori = binding.spCategory.selectedItem.toString()

            if (judul.isEmpty()) {
                showCustomToast("Judul kuis harus diisi!")
            }
            else if (kategori == "Pilih Kategori") {
                showCustomToast("Silakan pilih kategori!")
            }
            else {
                // --- JIKA SUKSES ---

                // 1. Tampilkan pesan sukses (Opsional)
                // Toast.makeText(this, "Kuis '$judul' berhasil dibuat!", Toast.LENGTH_SHORT).show()

                // 2. PINDAH KE HALAMAN TAMBAH PERTANYAAN
                val intent = Intent(this, AddQuestionActivity::class.java)

                // (Opsional) Kalau mau kirim data judul ke halaman sebelah:
                // intent.putExtra("QUIZ_TITLE", judul)

                startActivity(intent)
            }
        }
    }

    // --- FUNGSI CUSTOM TOAST ---
    private fun showCustomToast(message: String) {
        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast, null)

        val textView = layout.findViewById<TextView>(R.id.tvToastMessage)
        textView.text = message

        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 150)
        toast.show()
    }
}