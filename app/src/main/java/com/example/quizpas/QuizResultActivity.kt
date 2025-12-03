package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityQuizResultBinding

class QuizResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- BAGIAN BARU: MENANGKAP DATA SKOR ---
        // Mengambil data yang dikirim dari QuizGameActivity
        val score = intent.getIntExtra("SKOR_AKHIR", 0)
        val benar = intent.getIntExtra("JUMLAH_BENAR", 0)
        val totalSoal = intent.getIntExtra("TOTAL_SOAL", 0)

        // Menghitung jumlah salah (Opsional, untuk info tambahan)
        val salah = totalSoal - benar

        // --- MENAMPILKAN SKOR KE LAYAR ---
        // Pastikan di XML kamu TextView skor memiliki id: android:id="@+id/tvScore"
        binding.tvScore.text = "Skor Anda: $score"

        // (Opsional) Jika kamu ingin mengubah teks deskripsi, pastikan TextView deskripsi di XML punya ID juga
        // binding.tvDeskripsi.text = "Anda menjawab $benar benar dan $salah salah."

        // 1. Tombol Close (X) -> Ke Home
        binding.btnClose.setOnClickListener {
            goToHome()
        }

        // 2. Tombol Ulangi Kuis -> Ke Halaman Kategori
        binding.btnRepeatQuiz.setOnClickListener {
            val intent = Intent(this, KategoriActivity::class.java)
            // Menghapus history agar bersih saat mulai lagi
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        // 3. Tombol Kembali ke Beranda -> Ke Home
        binding.btnBackHome.setOnClickListener {
            goToHome()
        }

        // 4. Navigasi Bawah
        binding.btnNavHome.setOnClickListener { goToHome() }

        binding.btnNavCategory.setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }

        binding.btnNavProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    // Fungsi pindah ke Home dan hapus history back
    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}