package com.example.sqliteejercicio

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.FileObserver.DELETE
import java.sql.Types.INTEGER

class miSQLiteOpenHelper (context: Context) :SQLiteOpenHelper (context,"baseDeDatosContactos.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE tabla_contactos (_id INTEGER PRIMARY KEY , nombre TEXT, nombre2 TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /*
        if (oldVersion ==1)
            db?.execSQL("ALTER TABLE contactos ADD COLUMN codigopostal INTEGER")
        if (oldVersion <=2)
            db?.execSQL("ALTER TABLE contactos ADD COLUMN poblacion TEXT")
        if (oldVersion <=3)
            db?.execSQL("ALTER TABLE contactos ADD COLUMN pais TEXT")
        if (oldVersion >3)
            throw IllegalStateException ("Version desconocida $oldVersion")
            /
         */
    }

    fun insertDato (nombre: String, tlf:String) {
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("nombre2", tlf)

        val db = this.writableDatabase // Pedimos un acceso a la base de datos en modo escritura
        db.insert("tabla_contactos", null, datos)
        db.close() // cerramos el acceso a la base de datos
    }

    fun borrarDato (id : Int){
        val arg  = arrayOf(id.toString())
        val db = this.writableDatabase

        //val borrados = db.delete("tabla_contactos","ID = ?",arg)
        db.execSQL("DELETE FROM tabla_contactos WHERE _id = ?",arg)
        db.close()
    }

    fun updateDato (id:Int, nombre: String, tlf:String) {
        val arg  = arrayOf(id.toString())
        val datos = ContentValues()

        datos.put("nombre", nombre)
        datos.put("nombre2", tlf)

        val db = this.writableDatabase // Pedimos un acceso a la base de datos en modo escritura
        db.update("tabla_contactos", datos,"_id = ?",arg)
        db.close() // cerramos el acceso a la base de datos
    }

}