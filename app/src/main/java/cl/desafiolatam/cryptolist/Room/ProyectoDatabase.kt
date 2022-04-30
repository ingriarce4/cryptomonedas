package cl.desafiolatam.cryptolist.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cl.desafiolatam.cryptolist.Dao.CryptoDao
import cl.desafiolatam.cryptolist.modelo.Data

@Database(entities = [Data::class], version = 1 )
abstract class ProyectoDatabase :RoomDatabase(){

    abstract  fun cryptoDao(): CryptoDao

    companion object{
        @Volatile
        private var instancia:ProyectoDatabase? = null

        fun getInstancia(context: Context):ProyectoDatabase
        {
            if (instancia ==null)
            {
                synchronized(this)
                {
                    instancia = Room.databaseBuilder(context,
                        ProyectoDatabase::class.java,
                        "cryto_db")
                        .build()
                }

            }
            return instancia!!
        }

    }


}