package com.mixcorp.calculodeimc

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mixcorp.calculodeimc.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        verifica()
    }

    private fun verifica(){
        val imc = intent.getFloatExtra("imc",0.0f)
        val idade = intent.getIntExtra("idade", 0)
        val imcS = "Seu IMC é " + String.format("%.2f",imc)
        binding.textResultImc.text = imcS

        if (idade > 59){
            idososImc(imc)
        } else{
            adultosImc(imc)
        }
    }

    private fun adultosImc(imc:Float){
        if(imc < 18.5){
            binding.textSituation.text = getString(R.string.abaixo_peso)
            binding.textRecomends.text = getString(R.string.recomends)
        }
    }

    private fun idososImc(imc:Float){

    }

}

