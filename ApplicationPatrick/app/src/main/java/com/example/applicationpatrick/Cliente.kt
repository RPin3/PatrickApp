package com.example.applicationpatrick

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Cliente : AppCompatActivity() {
    private lateinit var txtCliente: TextView
    private lateinit var btnIngresar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)
        iniciarComponente()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    public fun iniciarComponente() {
        txtCliente = findViewById(R.id.txtCliente) as EditText
        btnRegresar = findViewById(R.id.btnRegresar) as Button
        btnIngresar = findViewById(R.id.btnEntrar) as Button
    }

    public fun eventosClic() {
        btnIngresar.setOnClickListener(View.OnClickListener {
            if(txtCliente.text.toString().contentEquals(charSequence = "")){
                Toast.makeText( this,  "Falto capturar el nombre del cliente", Toast.LENGTH_SHORT).show()
                txtCliente.requestFocus()
            } else {
                val intent = Intent( this, CotizacionActivity::class.java)
                intent.putExtra( "Cliente", txtCliente.text.toString())
                startActivity(intent)
            }
        })
        btnRegresar.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
    }