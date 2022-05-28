package com.mixcorp.calculodeimc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mixcorp.calculodeimc.R.color.red
import com.mixcorp.calculodeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {

        binding.btnCalcular.setOnClickListener{
           calcularIMC(
               binding.pesoEdt.text.toString(),
               binding.alturaEdt.text.toString(),
               binding.idadeEdt.text.toString())
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun calcularIMC(paramPeso: String, paramAltura: String, paramIdade: String){
        val peso = paramPeso.toFloatOrNull()
        val altura = paramAltura.toFloatOrNull()
        val idade = paramIdade.toIntOrNull()

            if (peso == null){
                binding.pesoEdt.setBackgroundColor(red)
                binding.pesoEdt.setHint("Prencha o campo peso")
            } else if (altura == null) {
                binding.alturaEdt.setBackgroundColor(red)
                binding.alturaEdt.setHint("Preencha o campo altura")
            }
            else if (idade == null) {
                binding.idadeEdt.setBackgroundColor(red)
                binding.idadeEdt.setHint("Preencha o campo idade")
            } else {
                val imc = peso / (altura * altura) //cálculo do imc
                //val imc = String.format("%.2f",valorImc)

                //passando os valores para a activity result
                val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("imc",imc)
                    intent.putExtra("idade",idade)
                startActivity(intent)
            }
    }
}

