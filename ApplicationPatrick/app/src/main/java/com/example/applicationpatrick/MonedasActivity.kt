package com.example.applicationpatrick

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MonedasActivity : AppCompatActivity() {
    private lateinit var txtCantidad: EditText
    private lateinit var spnConvertidor: Spinner
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var txtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monedas)
        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarComponentes() {
        txtCantidad = findViewById(R.id.txtCantidad)
        spnConvertidor = findViewById(R.id.spnConversion)
        btnCerrar = findViewById(R.id.btnCerrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCalcular = findViewById(R.id.btnCalcular)
        txtResultado = findViewById(R.id.txtResultado)

        // Llenar el spinner con el adaptador
        val items = resources.getStringArray(R.array.array_conversiones)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnConvertidor.adapter = adapter
    }

    private fun eventosClick() {
        var pos: Int = 0

        spnConvertidor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.getItemAtPosition(position).toString()
                Toast.makeText(applicationContext, "Seleccionaste $item", Toast.LENGTH_SHORT).show()
                pos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnCalcular.setOnClickListener {
            val dolara = resources.getString(R.string.dolara).toFloat()
            val dolarc = resources.getString(R.string.dolarc).toFloat()
            val euro = resources.getString(R.string.euro).toFloat()

            if (txtCantidad.text.toString().isEmpty()) {
                Toast.makeText(applicationContext, "Falto capturar la cantidad", Toast.LENGTH_SHORT).show()
            } else {
                val cantidad = txtCantidad.text.toString().toFloat()
                val resultado = when (pos) {
                    0 -> cantidad / dolara
                    1 -> cantidad / dolarc
                    2 -> cantidad / euro
                    3 -> cantidad * dolara
                    4 -> cantidad * dolarc
                    5 -> cantidad * euro
                    else -> 0.0f
                }
                txtResultado.text = resultado.toString()
            }
        }

        btnLimpiar.setOnClickListener {
            txtCantidad.text.clear()
            txtResultado.text = ""
        }

        btnCerrar.setOnClickListener {
            spnConvertidor.setSelection(0)
            finish()
        }
    }
}
