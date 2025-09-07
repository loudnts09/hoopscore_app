package com.example.pratica2_java

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    private var placarTimeA:Int = 0
    private var placarTimeB:Int = 0

    private var streakA = 0
    private var streakB = 0

    private val STREAK_THRESHOLD = 3


    private lateinit var pTimeA: TextView
    private lateinit var pTimeB: TextView
    private lateinit var nomeTimeA:TextView
    private lateinit var nomeTimeB:TextView

    private lateinit var fireA: TextView
    private lateinit var fireB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_main)

        pTimeA = findViewById(R.id.placarTimeA)
        pTimeB = findViewById(R.id.placarTimeB)
        fireA  = findViewById(R.id.fireA)
        fireB  = findViewById(R.id.fireB)
        nomeTimeA = findViewById(R.id.timeA)
        nomeTimeB = findViewById(R.id.timeB)
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
        nomeTimeA.setOnClickListener {
            editarNomeTime(nomeTimeA)
        }
        nomeTimeB.setOnClickListener {
            editarNomeTime(nomeTimeB)
        }
    }

    fun adicionarPontos(pontos: Int, time: String){
        if (time == "A"){
            placarTimeA += pontos
            streakA += 1
            streakB = 0
            pTimeA.text = placarTimeA.toString()
            checarFogo("A")
        }
        else{
            placarTimeB += pontos
            streakB += 1
            streakA = 0
            pTimeB.text = placarTimeB.toString()
            checarFogo("B")
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
    private fun checarFogo(time: String) {
        if (time == "A") {
            if (streakA >= STREAK_THRESHOLD) fireA.visibility = View.VISIBLE
            else fireA.visibility = View.GONE
            fireB.visibility = if (streakB >= STREAK_THRESHOLD) View.VISIBLE else View.GONE
        } else {
            if (streakB >= STREAK_THRESHOLD) fireB.visibility = View.VISIBLE
            else fireB.visibility = View.GONE
            fireA.visibility = if (streakA >= STREAK_THRESHOLD) View.VISIBLE else View.GONE
        }
    }

    fun reiniciarPartida(){
        placarTimeA = 0
        pTimeA.setText(placarTimeA.toString())
        placarTimeB = 0
        pTimeB.setText(placarTimeB.toString())
        streakA = 0; streakB = 0
        fireA.visibility = View.GONE
        fireB.visibility = View.GONE
        Toast.makeText(this, "Placar reiniciado", Toast.LENGTH_SHORT).show()
    }
    fun editarNomeTime(textView: TextView) {
        val input = android.widget.EditText(this)
        input.setText(textView.text.toString())

        android.app.AlertDialog.Builder(this)
            .setTitle("Alterar nome do time")
            .setView(input)
            .setPositiveButton("OK") { _, _ ->
                val novoNome = input.text.toString()
                if (novoNome.isNotBlank()) {
                    textView.text = novoNome
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}