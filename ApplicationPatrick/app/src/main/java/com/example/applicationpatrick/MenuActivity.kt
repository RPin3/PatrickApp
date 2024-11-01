package com.example.applicationpatrick

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    private lateinit var crvHola : CardView;
    private lateinit var crvIMC : CardView;
    private lateinit var crvConvetidor : CardView;
    private lateinit var crvConvertidor : CardView;
    private lateinit var crvCotizacion : CardView;
    private lateinit var crvSalida : CardView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        IniciarComponentes()
        eventosClick()
    }
    fun IniciarComponentes(){
        crvHola = findViewById(R.id.crvHola) as CardView
        crvIMC = findViewById(R.id.crvIMC) as CardView
        crvConvetidor = findViewById(R.id.crvConvetidor) as CardView
        crvConvertidor = findViewById(R.id.crvConvertidor) as CardView
        crvCotizacion = findViewById(R.id.crvCotizacion) as CardView
        crvSalida = findViewById(R.id.crvSalida) as CardView
    }
    fun eventosClick(){
        crvHola.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        crvIMC.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, ImcActivity::class.java)
            startActivity(intent)
        })
        crvConvetidor.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Convertidor::class.java)
            startActivity(intent)
        })
        crvSalida.setOnClickListener {
            finish()
        }
    }
}