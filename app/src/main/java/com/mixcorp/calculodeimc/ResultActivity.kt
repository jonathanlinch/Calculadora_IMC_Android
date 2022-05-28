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
            binding.textSituation.text = getString(R.string.baixoPeso)
            binding.textRecomends.text = getString(R.string.baixo_peso)
        } else if (24.9 > imc && imc > 18.5){
            binding.textSituation.text = getString(R.string.pesoNormal)
            binding.textRecomends.text = getString(R.string.peso_normal)
        } else if (29.9 > imc && imc > 25){
            binding.textSituation.text = getString(R.string.sobrePeso)
            binding.textRecomends.text = getString(R.string.sobre_peso)
        } else if (34.9 > imc && imc > 30){
            binding.textSituation.text = getString(R.string.obesidade1)
            binding.textRecomends.text = getString(R.string.obesidade_1)
        } else if (39.9 > imc && imc > 35){
            binding.textSituation.text = getString(R.string.obesidade2)
            binding.textRecomends.text = getString(R.string.obesidade_2)
        } else if (imc > 40){
            binding.textSituation.text = getString(R.string.obesidade3)
            binding.textRecomends.text = getString(R.string.obesidade_3)
        }
    }

    private fun idososImc(imc:Float){
        if (imc < 22){
            binding.textSituation.text = getString(R.string.baixoPeso)
            binding.textRecomends.text = getString(R.string.baixo_peso)
        } else if (27 > imc && imc > 22){
            binding.textSituation.text = getString(R.string.pesoNormal)
            binding.textRecomends.text = getString(R.string.peso_normal)
        } else if ( imc > 27){
            binding.textSituation.text = getString(R.string.obesidade1)
            binding.textRecomends.text = getString(R.string.obesidade_1)
        }
    }
}

