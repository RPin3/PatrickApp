package com.example.applicationpatrick

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcActivity : AppCompatActivity() {
    private lateinit var txtAltura: EditText
    private lateinit var txtPeso: EditText
    private lateinit var txtResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarComponentes()
        eventClic()
    }
    private fun iniciarComponentes() {
        txtAltura = findViewById(R.id.txtAltura)
        txtPeso = findViewById(R.id.txtPeso)
        txtResultado = findViewById(R.id.txtResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    private fun eventClic() {
        btnCalcular.setOnClickListener(View.OnClickListener {
            val pesoText = txtPeso.text.toString()
            val alturaText = txtAltura.text.toString()

            if (pesoText.isEmpty() || alturaText.isEmpty()) {
                Toast.makeText(this, "Falta capturar información", Toast.LENGTH_SHORT).show()
            } else {
                val peso: Float = pesoText.toFloat()
                val altura: Float = alturaText.toFloat()
                val imc: Float = peso / (altura * altura)
                txtResultado.text = imc.toString()
            }
        })

        btnLimpiar.setOnClickListener(View.OnClickListener {
            txtResultado.text = ""
            txtPeso.text.clear()
            txtAltura.text.clear()
        })

        btnCerrar.setOnClickListener(View.OnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("IMC")
            builder.setMessage("¿Desea salir?")
            builder.setPositiveButton("Aceptar") { _, _ ->
                finish()
            }
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        })
    }
}