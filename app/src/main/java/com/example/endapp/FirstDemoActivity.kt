package com.example.endapp

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.endapp.databinding.ActivityFirstDemoBinding
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

class FirstDemoActivity :AppCompatActivity() {

    private lateinit var binding: ActivityFirstDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val word = WordExtra(
            "Galaxy",
            "Галактика"
        )

        binding.btnGoToSecond.setOnClickListener(){
            val intent = Intent(this@FirstDemoActivity,SecondDemoActivity::class.java)
            intent.putExtra("EXTRA_KEY_WORD", word)
            startActivity(intent)
        }
    }
    @Parcelize
    data class WordExtra(
        val original: String,
        val translated: String,
        var learned: Boolean = false,
    ) : Parcelable

}