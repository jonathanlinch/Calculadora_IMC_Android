package com.mixcorp.calculodeimc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

@Suppress("NAME_SHADOWING")
const val valorImc = "com.mixcorp.calculodeimc.IMC"
@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()

    }

    private fun setListeners() {
        val pesoEdt = findViewById<EditText>(R.id.pesoEdt)
        val alturaEdt = findViewById<EditText>(R.id.alturaEdt)
        val idadeEdt = findViewById<EditText>(R.id.idadeEdt)
        val calcularBtn = findViewById<Button>(R.id.btnCalcular)

        calcularBtn.setOnClickListener{
           calcularIMC(pesoEdt.text.toString(), alturaEdt.text.toString(), idadeEdt.text.toString())
        }
    }

    private fun calcularIMC(peso: String, altura: String, idade: String){
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()
        val idade = idade.toIntOrNull()

            if (peso != null && altura != null && idade != null) {
                val imc = peso / (altura * altura)
                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra(valorImc,imc)
                }
                startActivity(intent)
        }


    }
}

