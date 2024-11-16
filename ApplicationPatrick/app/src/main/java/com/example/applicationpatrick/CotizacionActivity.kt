package com.example.applicationpatrick

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CotizacionActivity : AppCompatActivity() {

    // Componentes del layout
    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPorPagI: EditText
    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton
    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    // Instancia de la clase lógica
    private val cotizacion = Constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cotizacion)

        iniciarComponentes()
        eventosClic()
    }

    private fun iniciarComponentes() {
        txtCliente = findViewById(R.id.txtCliente)
        txtFolio = findViewById(R.id.txtFolio)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtPorPagI = findViewById(R.id.txtPorPagI)
        txtPagoInicial = findViewById(R.id.txtPagoInicial)
        txtPagoMensual = findViewById(R.id.txtPagoMensual)
        txtTotalFin = findViewById(R.id.txtTotalFin)
        rdb12 = findViewById(R.id.rdb12)
        rdb24 = findViewById(R.id.rdb24)
        rdb36 = findViewById(R.id.rdb36)
        rdb48 = findViewById(R.id.rdb48)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)

        val strCliente: String = intent.getStringExtra("Cliente") ?: "Cliente desconocido"
        txtCliente.text = "Nombre del Cliente: $strCliente"

        val folio: Int = cotizacion.generaFolio()
        txtFolio.text = "Folio: $folio"
    }

    private fun eventosClic() {
        btnCalcular.setOnClickListener {
            if (txtDescripcion.text.isBlank() || txtPrecio.text.isBlank() || txtPorPagI.text.isBlank()) {
                Toast.makeText(this, "Faltó capturar algún dato", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            cotizacion.descripcion = txtDescripcion.text.toString()
            cotizacion.precio = txtPrecio.text.toString().toFloat()
            cotizacion.porPagInicial = txtPorPagI.text.toString().toFloat()

            cotizacion.plazos = when {
                rdb12.isChecked -> 12
                rdb24.isChecked -> 24
                rdb36.isChecked -> 36
                rdb48.isChecked -> 48
                else -> 0
            }

            if (cotizacion.plazos == 0) {
                Toast.makeText(this, "Selecciona un plazo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            txtPagoInicial.text = "Pago Inicial: $${"%.2f".format(cotizacion.calcularPagoInicial())}"
            txtTotalFin.text = "Total a Financiar: $${"%.2f".format(cotizacion.calcularTotalFin())}"
            txtPagoMensual.text = "Pago Mensual: $${"%.2f".format(cotizacion.calcularPagoMensual())}"
        }

        btnLimpiar.setOnClickListener {
            txtFolio.text = "Folio"
            txtPagoInicial.text = "Pago Inicial"
            txtTotalFin.text = "Total a Financiar"
            txtPagoMensual.text = "Pago Mensual"

            txtDescripcion.text.clear()
            txtPrecio.text.clear()
            txtPorPagI.text.clear()

            rdb12.isChecked = true
        }

        btnCerrar.setOnClickListener {
            finish()
        }
    }
}
