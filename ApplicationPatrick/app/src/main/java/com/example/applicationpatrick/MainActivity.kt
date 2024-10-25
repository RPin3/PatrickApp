package com.example.applicationpatrick

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var txtNombre: EditText
    private lateinit var txtSaludo: TextView
    private lateinit var btnPulsar: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarComponentes()
        eventosClic()

        // Configurar insets para ajustar la vista a las barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarComponentes() {
        txtNombre = findViewById(R.id.txtNombre)
        txtSaludo = findViewById(R.id.tvSaludo)
        btnPulsar = findViewById(R.id.btnPulsar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
    }

    private fun eventosClic() {
        btnPulsar.setOnClickListener {
            val nombre = txtNombre.text.toString()
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Falto capturar el nombre", Toast.LENGTH_SHORT).show()
            } else {
                txtSaludo.text = "Hola $nombre, ¿cómo estás?"
            }
        }

        btnLimpiar.setOnClickListener {
            txtSaludo.text = ""
            txtNombre.setText("")
        }

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}