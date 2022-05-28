package com.mixcorp.calculodeimc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val imc = intent.getStringExtra(valorImc)
        val idade = intent.getStringExtra(valorIdade)

        if (imc != null && idade != null) {
                verifica(imc.toFloat(), idade.toFloat())
            }
        }

    private fun verifica(imc:Float, idade:Float){

    }

    private fun criancasImc(imc:Float, idade:Float){

    }

    private fun adultosImc(imc:Float, idade:Float){

    }

    private fun idososImc(imc:Float, idade:Float){

    }

}
