package br.com.danielsouza.eletriccarapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
        setupListeners()
    }

    fun setupViews() {
        preco = findViewById(R.id.et_preco_kwh)
        kmPercorrido = findViewById(R.id.et_km_percorrido)
        btnCalcular = findViewById(R.id.btn_calcular)
        resultado = findViewById(R.id.tv_resultado)
    }


    fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }
    }

    fun calcular(){
        val preco = preco.text.toString().toFloat()
        val km = kmPercorrido.text.toString().toFloat()

        val result = preco/km

        resultado.text = result.toString()
    }
}