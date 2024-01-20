package br.com.danielsouza.eletriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.danielsouza.eletriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_BATERIA
import br.com.danielsouza.eletriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_CAR_ID
import br.com.danielsouza.eletriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_POTENCIA
import br.com.danielsouza.eletriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_PRECO
import br.com.danielsouza.eletriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_RECARGA
import br.com.danielsouza.eletriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO
import br.com.danielsouza.eletriccarapp.data.local.CarrosContract.CarEntry.TABLE_NAME
import br.com.danielsouza.eletriccarapp.domain.Carro

class CarRepository(private val context: Context) {
    fun save(carro: Carro): Boolean {
        var isSaved = false
        try {
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put(COLUMN_NAME_CAR_ID, carro.id)
                put(COLUMN_NAME_PRECO, carro.preco)
                put(COLUMN_NAME_BATERIA, carro.bateria)
                put(COLUMN_NAME_POTENCIA, carro.potencia)
                put(COLUMN_NAME_RECARGA, carro.recarga)
                put(COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
            }

            val inserted = db?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values)
            if (inserted != null) {
                isSaved = true
            }
        } catch (ex: Exception) {
            ex.message?.let { Log.e("Erro -> ", it) }
        }

        return isSaved
    }

    fun findCarById(id: Int): Carro {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )

        val filter = "$COLUMN_NAME_CAR_ID = ? "
        val filterValues = arrayOf(id.toString())
        val cursor = db.query(
            TABLE_NAME,
            columns,
            filter,
            filterValues,
            null,
            null,
            null
        )

        var itemId: Long = 0
        var preco = ""
        var bateria = ""
        var potencia = ""
        var recarga = ""
        var urlPhoto = ""

        with(cursor) {
            while (moveToNext()) {
                itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                bateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA))
                potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                recarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA))
                urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))
            }
        }
        cursor.close()

        return Carro(
            id = itemId.toInt(),
            preco = preco,
            bateria = bateria,
            potencia = potencia,
            recarga = recarga,
            urlPhoto = urlPhoto,
            isFavorite = true
        )
    }

    fun deleteCarById(id: Int): Boolean {
        var isDeleted = false
        try{
            val dbHelper = CarsDbHelper(context)
            val db = dbHelper.writableDatabase

            val filter = "$COLUMN_NAME_CAR_ID = ?"
            val filterArgs = arrayOf(id.toString())


            val deleted = db.delete(
                TABLE_NAME,
                filter,
                filterArgs
            )

            if(deleted > 0){
               isDeleted = true
            }
        }catch (ex: Exception){
            ex.message?.let { Log.e("Erro ->", it) }
        }

        return isDeleted
    }

    fun saveIfNotExist(carro: Carro) {
        val car = findCarById(carro.id)
        if (car.id == ID_WHEN_NO_CAR) {
            save(carro)
        }
    }

    fun getAll(): List<Carro> {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase

        val columns = arrayOf(
            BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO
        )

        val cursor = db.query(
            TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        val carros = mutableListOf<Carro>()

        with(cursor) {
            while (moveToNext()) {
                val itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                val preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                val bateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA))
                val potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                val recarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA))
                val urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))

                carros.add(
                    Carro(
                        id = itemId.toInt(),
                        preco = preco,
                        bateria = bateria,
                        potencia = potencia,
                        recarga = recarga,
                        urlPhoto = urlPhoto,
                        isFavorite = true
                    )
                )
            }
        }
        cursor.close()

        return carros
    }

    companion object {
        const val ID_WHEN_NO_CAR = 0
    }
}