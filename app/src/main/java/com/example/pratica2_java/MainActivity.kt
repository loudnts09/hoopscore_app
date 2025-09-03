package com.example.pratica2_java

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private var placarTimeA:Int = 0
    private var placarTimeB:Int = 0
    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)

        val bTresPontosTimeA:Button = findViewById(R.id.tresPontosA)
        val bTLivreTimeA: Button = findViewById(R.id.tiroLivreA)
        val bDoisPontosTimeA: Button = findViewById(R.id.doisPontosA)
        val bTresPontosTimeB: Button = findViewById(R.id.tresPontosB)
        val bDoisPontosTimeB: Button = findViewById(R.id.doisPontosB)
        val bTLivreTimeB: Button = findViewById(R.id.tiroLivreB)
        val bReiniciar: Button = findViewById(R.id.reiniciarPartida)

        bTresPontosTimeA.setOnClickListener {
            adicionarPontos(3, "A")
        }
        bDoisPontosTimeA.setOnClickListener {
            adicionarPontos(2, "A")
        }
        bTLivreTimeA.setOnClickListener {
            adicionarPontos(1, "A")
        }
        bTresPontosTimeB.setOnClickListener {
            adicionarPontos(3, "B")
        }
        bDoisPontosTimeB.setOnClickListener {
            adicionarPontos(2, "B")
        }
        bTLivreTimeB.setOnClickListener {
            adicionarPontos(1, "B")
        }
        bReiniciar.setOnClickListener {
            reiniciarPartida()
        }
    }

    fun adicionarPontos(pontos: Int, time: String){
        if (time == "A"){
            placarTimeA += pontos
        }
        else{
            placarTimeB += pontos
        }
        atualizarPlacar(time)
    }

    fun atualizarPlacar(time: String){
        if(time == "A"){
            pTimeA.setText(placarTimeA.toString())
        }
        else{
            pTimeB.setText(placarTimeB.toString())
        }
    }

    fun reiniciarPartida(){
        placarTimeA = 0
        pTimeA.setText(placarTimeA.toString())
        placarTimeB = 0
        pTimeB.setText(placarTimeB.toString())
        Toast.makeText(this, "Placar reiniciado", Toast.LENGTH_SHORT).show()
    }
}