package com.example.castillo_19100253_dam_pc2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.compose.material3.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.material.snackbar.Snackbar

import java.util.UUID

class PrincipalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        val txtNomb: EditText = findViewById(R.id.txtNombre)
        val txtAnio: EditText = findViewById(R.id.txtAnio)
        val txtTitulos: EditText = findViewById(R.id.txtTitulos)
        val txtUrl: EditText = findViewById(R.id.txtUrl)
        val btnSave: Button = findViewById(R.id.btnGuardar)
        val db = FirebaseFirestore.getInstance()

        btnSave.setOnClickListener {
            var nombre = txtNomb.text.toString()
            var anio = txtAnio.text.toString()
            var titulos = txtTitulos.text.toString()
            var url = txtUrl.text.toString()

            val nuevoCurso = Equipos(nombre, anio, titulos, url)

            val id: UUID = UUID.randomUUID()

            db.collection("equipos")
                .document(id.toString())
                .set(nuevoCurso)
                .addOnSuccessListener {
                    Snackbar
                        .make(
                            findViewById(android.R.id.content)
                            ,"Equipo Registrado con exito"
                            , Snackbar.LENGTH_LONG
                        ).show()
                }.addOnFailureListener{
                    Snackbar
                        .make(
                            findViewById(android.R.id.content)
                            ,"Ocurrió un problema al registrar el equipo"
                            , Snackbar.LENGTH_LONG
                        ).show()
                }
        }

    }
}

data class Equipos (
    val Nombre: String,
    val Anio: String,
    val Numero: String,
    val Url: String)
