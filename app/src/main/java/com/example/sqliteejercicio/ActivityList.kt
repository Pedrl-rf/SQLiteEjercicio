package com.example.sqliteejercicio

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.*
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteejercicio.databinding.ListItemBinding
import com.example.sqliteejercicio.databinding.ListViewBinding


class ActivityList : AppCompatActivity(){

    lateinit var binding: ListViewBinding
    lateinit var contactosHelper: miSQLiteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactosHelper = miSQLiteOpenHelper(this)

        val db : SQLiteDatabase = contactosHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tabla_contactos",null)
        val adaptador = CursosAdapterListView(this, cursor)
        binding.lvMainActivity.adapter = adaptador
        db.close()

    }


    inner class CursosAdapterListView(context: Context,cursor: Cursor):CursorAdapter(context,cursor,
        FLAG_REGISTER_CONTENT_OBSERVER){
        override fun newView(context: Context?, cursor: Cursor?,
                             parent: ViewGroup?): View {

            val inflater = LayoutInflater.from(context)
            var inflate =  inflater.inflate(R.layout.list_item,parent,false)
            return inflate
        }

        override fun bindView(view: View?, context: Context?,
                              cursor: Cursor?) {
            var bindingItems = ListItemBinding.bind(view!!)
            var id = cursor!!.getString(0)
            bindingItems.tvNombreListView.text = cursor!!.getString(1)
            bindingItems.tvTelefonoListView.text = cursor!!.getString(2)


            view.setOnClickListener{
                Toast.makeText(this@ActivityList,
                                "${bindingItems.tvNombreListView.text}",
                                Toast.LENGTH_SHORT).show()


                val builder = AlertDialog.Builder(context)
                val intent = Intent(context,MainActivity::class.java)
                builder.setTitle("${bindingItems.tvNombreListView.text}")
                builder.setMessage("¿Quieres modificalo?")

                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT).show()
                    intent.putExtra("nombre",bindingItems.tvNombreListView.text)
                    intent.putExtra("nombre2",bindingItems.tvTelefonoListView.text)
                    intent.putExtra("id",id)
                    startActivity(intent)
                }

                builder.setNegativeButton(android.R.string.no) { dialog, which ->
                    Toast.makeText(applicationContext,
                        android.R.string.no, Toast.LENGTH_SHORT).show()
                }

                builder.show()
            }



        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intentListView = Intent(this, MainActivity::class.java)
        startActivity(intentListView)
        return super.onOptionsItemSelected(item)
    }
}