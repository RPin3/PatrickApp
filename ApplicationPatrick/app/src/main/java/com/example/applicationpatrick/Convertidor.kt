package com.example.applicationpatrick

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Convertidor : AppCompatActivity() {
    private lateinit var txtCantidad: EditText
    private lateinit var txtResultado: TextView
    private lateinit var rbdCel: RadioButton
    private lateinit var rbdFar: RadioButton
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_convertidor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        iniciarComponentes()
        eventosClick()
    }
    private fun iniciarComponentes() {
        txtCantidad = findViewById(R.id.txtCantidad)
        txtResultado = findViewById(R.id.txtResultado)
        rbdCel = findViewById(R.id.rbdCel)
        rbdFar = findViewById(R.id.rbdFar)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    private fun eventosClick() {
        btnCalcular.setOnClickListener {
            val cantidadText = txtCantidad.text.toString()
            if (cantidadText.isEmpty()) {
                Toast.makeText(this, "Falta capturar entidad", Toast.LENGTH_SHORT).show()
            } else {
                val cantidad: Float = cantidadText.toFloat()
                if (rbdCel.isChecked) {
                    val fahrenheit = (cantidad * 9 / 5) + 32
                    txtResultado.text = fahrenheit.toString()
                } else if (rbdFar.isChecked) {
                    val celsius = (cantidad - 32) * 5 / 9
                    txtResultado.text = celsius.toString()
                }
            }
        }

        btnLimpiar.setOnClickListener {
            txtCantidad.text.clear()
            txtResultado.text = ""
        }

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}