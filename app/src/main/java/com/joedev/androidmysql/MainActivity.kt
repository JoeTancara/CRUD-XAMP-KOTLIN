package com.joedev.androidmysql

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    var txtNombre: EditText? = null
    var txtEmail: EditText? = null
    var txtTelefono: EditText? = null
    var txtPass: EditText? = null
    var tbUsuarios:TableLayout?=null
    var idGlobal: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtNombre = findViewById(R.id.txtNombre)
        txtEmail = findViewById(R.id.txtEmail)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtPass = findViewById(R.id.txtPass)
        tbUsuarios = findViewById(R.id.tbUsuarios)

        cargaTabla()



    }
    fun cargaTabla(){
        tbUsuarios?.removeAllViews()
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val url = "http://192.168.0.13/ANDROID_MYSQL/registros.php"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonArray = response.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val registro = LayoutInflater.from(this).inflate(R.layout.table_row_joe, null, false)
                        val colNombre = registro.findViewById<View>(R.id.colNombre) as TextView
                        val colEmail = registro.findViewById<View>(R.id.colEmail) as TextView
                        val colEditar = registro.findViewById<View>(R.id.colEditar)
                        val colBorrar = registro.findViewById<View>(R.id.colBorrar)
                        colNombre.text = jsonObject.getString("nombre")
                        colEmail.text = jsonObject.getString("email")
                        colEditar.id = jsonObject.getString("id").toInt()
                        colBorrar.id = jsonObject.getString("id").toInt()
                        tbUsuarios?.addView(registro)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            }
        )

        queue.add(jsonObjectRequest)
    }
    fun clicGuardarEditr(view: View){
        val url = "http://192.168.0.13/ANDROID_MYSQL/editar.php"
        val queue=Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener{ response ->
                Toast.makeText(this, "El usuario se actualizo de forma exitosa", Toast.LENGTH_SHORT).show()
                cargaTabla()
            },Response.ErrorListener{
                    error ->
                Toast.makeText(this, "Error al actualizar usuario $error", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val paramentros=HashMap<String, String>()
                paramentros.put("id",idGlobal!!)
                paramentros.put("nombre",txtNombre?.text.toString())
                paramentros.put("email",txtEmail?.text.toString())
                paramentros.put("telefono",txtTelefono?.text.toString())
                paramentros.put("pass",txtPass?.text.toString())
                return paramentros
            }
        }
        queue.add(resultadoPost)
    }
    fun clicTablaEditar(view: View){
        idGlobal=view.id.toString()
        val id:String?=intent.getStringExtra("id").toString()
        val queue : RequestQueue = Volley.newRequestQueue(this)
        val url = "http://192.168.0.13/ANDROID_MYSQL/registro.php?id=${idGlobal}"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                txtNombre?.setText(response.getString("nombre"))
                txtEmail?.setText(response.getString("email"))
                txtTelefono?.setText(response.getString("telefono"))
                txtPass?.setText(response.getString("pass"))
            }, { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(jsonObjectRequest)

    }
    fun clicTablaBorrar(view: View){
        val url = "http://192.168.0.13/ANDROID_MYSQL/borrar.php"
        val queue=Volley.newRequestQueue(this)
        var resultadoPost  = object : StringRequest(Request.Method.POST,url,
            Response.Listener { response ->
                Toast.makeText(this, "El usuario se elimino de forma exitosa", Toast.LENGTH_SHORT).show()
                cargaTabla()

            },Response.ErrorListener { error ->
                Toast.makeText(this, "Error al eliminar usuario $error", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val paramentros=HashMap<String, String>()
                paramentros.put("id",view.id.toString())
                return paramentros
            }
        }
        queue.add(resultadoPost)
    //        Toast.makeText(this, view.id.toString(), Toast.LENGTH_LONG).show()
    }
    fun clickBtnInsertar(view: View) {
        val url = "http://192.168.0.13/ANDROID_MYSQL/insertar.php"
        val queue: RequestQueue = Volley.newRequestQueue(this)
        val request: StringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()
                cargaTabla()

            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["nombre"] = txtNombre?.text.toString()
                params["email"] = txtEmail?.text.toString()
                params["telefono"] = txtTelefono?.text.toString()
                params["pass"] = txtPass?.text.toString()
                return params
            }
        }
        queue.add(request)
    }
    fun clicReset(view: View){
        txtNombre?.setText("")
        txtEmail?.setText("")
        txtTelefono?.setText("")
        txtPass?.setText("")
    }

}