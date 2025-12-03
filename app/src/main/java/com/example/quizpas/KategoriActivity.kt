package com.example.quizpas

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizpas.databinding.ActivityKategoriBinding

class KategoriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKategoriBinding
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. SIAPKAN DATA KATEGORI
        val dataList = listOf(
            CategoryModel("Sejarah", R.drawable.img_sejarah),
            CategoryModel("Geografi", R.drawable.img_geografi),
            CategoryModel("Sains", R.drawable.img_sains),
            CategoryModel("Matematika", R.drawable.img_matematika),
            CategoryModel("Literatur", R.drawable.img_literatur),
            CategoryModel("Seni", R.drawable.img_seni)
        )

        // 2. SETUP RECYCLERVIEW ADAPTER
        categoryAdapter = CategoryAdapter(dataList) { category ->
            // Aksi Klik Item Kategori: Pindah ke halaman Kuis
            val intent = Intent(this, QuizGameActivity::class.java)
            intent.putExtra("KATEGORI_TERPILIH", category.name)
            startActivity(intent)
        }

        // 3. PASANG KE TAMPILAN
        binding.rvKategori.apply {
            layoutManager = GridLayoutManager(this@KategoriActivity, 2)
            adapter = categoryAdapter
            setHasFixedSize(true)
        }

        // --- BAGIAN BARU: TOMBOL TAMBAH KUIS (+) ---
        // Pastikan ID di XML sudah ditambahkan: android:id="@+id/btnAddQuiz"
        binding.btnAddQuiz.setOnClickListener {
            val intent = Intent(this, CreateQuizActivity::class.java)
            startActivity(intent)
        }
        // -------------------------------------------

        // 4. NAVIGASI BAWAH
        binding.btnNavHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        binding.btnNavProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Tombol Kategori diam saja
        binding.btnNavCategory.setOnClickListener { }
    }
}