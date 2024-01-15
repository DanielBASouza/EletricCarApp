package br.com.danielsouza.eletriccarapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import br.com.danielsouza.eletriccarapp.R

class MainActivity : AppCompatActivity() {
    lateinit var btnRedirect: Button
    lateinit var ivCarRedirect: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
    }

    fun setupViews() {
        btnRedirect = findViewById(R.id.btn_redirect)
        ivCarRedirect = findViewById(R.id.iv_image)
    }


    fun setupListeners() {
        btnRedirect.setOnClickListener {
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
        ivCarRedirect.setOnClickListener {
            startActivity(Intent(this, CalcularAutonomiaActivity::class.java))
        }
    }
}