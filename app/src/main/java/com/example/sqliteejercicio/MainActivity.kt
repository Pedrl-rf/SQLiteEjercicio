package com.example.sqliteejercicio

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sqliteejercicio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var contactosHelper: miOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        contactosHelper = miOpenHelper(this)

        binding.btGuardar.setOnClickListener{
            if (binding.etNombre.text.isNotBlank() && binding.etNombre2.text.isNotBlank()){

                contactosHelper.insertDato(binding.etNombre.text.toString(), binding.etNombre2.text.toString())

                binding.etNombre2.text.clear()
                binding.etNombre.text.clear()

                Toast.makeText(this,"Guargadado",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btVerBBDD.setOnClickListener{
            binding.tvConsultaMain.text = ""
            val db : SQLiteDatabase = contactosHelper.readableDatabase

            val cursor = db.rawQuery("SELECT * FROM tabla_contactos",null)

            if (cursor.moveToFirst()){
                do {
                    binding.tvConsultaMain.append(
                        cursor.getInt(0).toString()+ " : "
                    )

                    binding.tvConsultaMain.append(
                        cursor.getString(1)+" , "
                    )

                    binding.tvConsultaMain.append(
                        cursor.getString(2)+" \n"
                    )

                }while (cursor.moveToNext())
            }
        }

        binding.btBorrar.setOnClickListener{
            var cantidad = 0

            if (binding.etNombre.text.isNotBlank() &&
                binding.etNombre2.text.isNotBlank() &&
                binding.etId.text.isNotBlank()){

                cantidad = contactosHelper.borrarDato(binding.etId.toString().toInt())

                binding.etId.text.clear()

                Toast.makeText(this,"Borrado con la id : ${cantidad}",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"No se ha podido borrar" ,Toast.LENGTH_SHORT).show()
            }
        }

        binding.btModificar.setOnClickListener{
            if (binding.etNombre.text.isNotBlank() &&
                binding.etNombre2.text.isNotBlank() &&
                binding.etId.text.isNotBlank()){

                contactosHelper.updateDato( binding.etId.text.toString().toInt(),
                                            binding.etNombre.text.toString(),
                                            binding.etNombre2.text.toString())
                binding.etId.text.clear()
                binding.etNombre2.text.clear()
                binding.etNombre.text.clear()

                Toast.makeText(this,"Modificado",Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(this,"Erorr al ser modificado",Toast.LENGTH_SHORT).show()
            }
        }


    }
}