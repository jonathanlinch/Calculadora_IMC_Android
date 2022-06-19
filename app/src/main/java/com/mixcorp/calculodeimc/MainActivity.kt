package com.mixcorp.calculodeimc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.mixcorp.calculodeimc.R.color.red
import com.mixcorp.calculodeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mInterstitialAd: InterstitialAd? = null
    private var mAdIsLoading: Boolean = false
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this){}
        startGame()
        setListeners()
    }
    private fun setListeners() {

        binding.btnCalcular.setOnClickListener{
            calcularIMC(
               binding.pesoEdt.text.toString(),
               binding.alturaEdt.text.toString(),
               binding.idadeEdt.text.toString())
            showInterstitial()

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

    //adMob ---------------------------------------------
    private fun startGame(){
        if (!mAdIsLoading && mInterstitialAd == null) {
            mAdIsLoading = true
            loadAd()
        }
    }

    private fun loadAd(){
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712",
            adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    mAdIsLoading = false
/*
                    Toast.makeText(
                        this@MainActivity,
                        "Erro ao carregar anúncio",
                        Toast.LENGTH_SHORT
                    ).show()
*/
                }
                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    mAdIsLoading = false
/*
                    Toast.makeText(this@MainActivity, "onAdLoaded()",
                        Toast.LENGTH_SHORT).show()
*/
                }
            })
    }

    private fun showInterstitial() {
        if (mInterstitialAd != null) {
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    mInterstitialAd = null
                    loadAd()
                }
                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
/*
                    Toast.makeText(this@MainActivity, "falha ao exibir o anúncio",
                        Toast.LENGTH_SHORT).show()
*/
                    mInterstitialAd = null
                }
                override fun onAdShowedFullScreenContent() {

                    // Called when ad is dismissed.
                }
            }
            mInterstitialAd?.show(this)
        }else {
//            Toast.makeText(this, "Ad wasn't loaded.", Toast.LENGTH_SHORT).show()
        }
    }
}

