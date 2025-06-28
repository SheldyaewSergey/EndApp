package com.example.endapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.endapp.FirstDemoActivity.*
import com.example.endapp.databinding.ActivityFirstDemoBinding
import com.example.endapp.databinding.ActivitySecondDemoBinding

class SecondDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackToFirst.setOnClickListener() {
            val intent = Intent(this@SecondDemoActivity, FirstDemoActivity::class.java)
            startActivity(intent)
        }

        val word = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableArrayExtra("EXTRA_KEY_WORD", WordExtra::class.java)
        } else {
            intent.getSerializableExtra("EXTRA_KEY_WORD")
        }
    }
}