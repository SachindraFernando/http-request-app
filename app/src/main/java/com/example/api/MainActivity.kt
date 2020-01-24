package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpPost
import com.google.gson.Gson

data class Person( var name:String,
                   var age:String,
                   var address:String){ }

class MainActivity : AppCompatActivity() {
    private var person:Person?=null
    private var txtName: EditText?=null
    private var txtAge: EditText?=null
    private var txtAddress: EditText?=null
    private var btnSubmit: Button?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialise()
    }
    private fun initialise() {
        txtName=findViewById(R.id.name)
        txtAge=findViewById(R.id.age)
        txtAddress=findViewById(R.id.address)
        btnSubmit=findViewById(R.id.button)

        btnSubmit!!.setOnClickListener {
            person = Person(
                name=txtName?.text.toString(),
                age=txtAge?.text.toString(),
                address=txtAddress?.text.toString()
            )
            val personJson = Gson().toJson(person)
            //Toast.makeText(this@MainActivity,personJson,Toast.LENGTH_SHORT).show()
           //Fuel.get("http://localhost:3000/api/person").responseObject<Person>{
            "http://seapi.herokuapp.com/api/addPost".httpPost().header("Content-Type" to "application/json").body(personJson.toString())
                .response{
                        request, response, result ->
                    println(request)
                    println(response)
                    Toast.makeText(this@MainActivity,result.toString(),Toast.LENGTH_SHORT).show()
                    println(result.toString())
//                        val (bytes, error) = result
//                        if (bytes != null) {
//                            println("[response bytes] ${(bytes)}")
//                        }
                }
        }
    }
}
