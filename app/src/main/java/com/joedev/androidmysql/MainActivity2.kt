package com.joedev.androidmysql

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Request.Method.POST
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity2 : AppCompatActivity() {

    var txtNombre: EditText? = null
    var txtEmail: EditText? = null
    var txtTelefono: EditText? = null
    var txtPass: EditText? = null
    var tvId:TextView?=null
    var id:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        txtNombre=findViewById(R.id.txtNombre)
        txtEmail=findViewById(R.id.txtEmail)
        txtTelefono=findViewById(R.id.txtTelefono)
        txtPass=findViewById(R.id.txtPass)
        id=intent.getStringExtra("id").toString()
        tvId?.setText(id)

    }
    fun clicRegresar(view: View){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun clicBorrar(view: View){

    }
    fun clicEditar(view: View){
        val url = "http://192.168.0.13/ANDROID_MYSQL/editar.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener{ response ->
                Toast.makeText(this, "El usuario se actualizo de forma exitosa", Toast.LENGTH_SHORT).show()
            },Response.ErrorListener{
                error ->
                Toast.makeText(this, "Error al actualizar usuario $error", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val paramentros=HashMap<String, String>()
                paramentros.put("id",id!!)
                paramentros.put("nombre",txtNombre?.text.toString())
                paramentros.put("email",txtEmail?.text.toString())
                paramentros.put("telefono",txtTelefono?.text.toString())
                paramentros.put("pass",txtPass?.text.toString())
                return paramentros
            }
        }
        queue.add(resultadoPost)
    }
}