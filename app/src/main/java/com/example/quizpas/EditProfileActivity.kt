package com.example.quizpas

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.quizpas.databinding.ActivityEditProfileBinding

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding

    // 1. Variabel untuk menyimpan alamat foto yang dipilih
    private var imageUri: Uri? = null

    // 2. Siapkan Peluncur (Launcher) untuk membuka Galeri
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            imageUri = uri
            // Tampilkan foto yang dipilih ke ImageView (ivProfile)
            binding.ivProfile.setImageURI(uri)
        } else {
            Toast.makeText(this, "Batal memilih foto", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. KLIK FOTO -> Buka Galeri
        binding.ivProfile.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        // 4. Tombol Kembali (Panah Kiri)
        binding.btnBack.setOnClickListener {
            finish() // Kembali ke halaman sebelumnya
        }

        // 5. Tombol Simpan
        binding.btnSave.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()

            // Cek apakah user mengganti foto atau tidak
            if (imageUri != null) {
                Toast.makeText(this, "Foto & Profil berhasil diperbarui!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Profil diperbarui (Tanpa ganti foto)", Toast.LENGTH_SHORT).show()
            }

            // Tutup halaman edit setelah simpan
            finish()
        }
    }
}