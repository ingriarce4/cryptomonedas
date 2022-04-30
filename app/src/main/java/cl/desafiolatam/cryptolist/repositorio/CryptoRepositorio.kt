package cl.desafiolatam.cryptolist.repositorio

import android.content.Context
import androidx.lifecycle.LiveData
import cl.desafiolatam.cryptolist.Room.ProyectoDatabase
import cl.desafiolatam.cryptolist.modelo.Crypto
import cl.desafiolatam.cryptolist.modelo.Data
import cl.desafiolatam.cryptolist.ui.ListaMonedas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CryptoRepositorio (var context: Context) {

    private val db = ProyectoDatabase.getInstancia(context)

    //METODOS DEL DAO
    fun agregar (monedas: List<Data>){

        //NECESITO UN SCOPE
        CoroutineScope(Dispatchers.IO).launch {
            db.cryptoDao().agregarCrypto(monedas)
        }

    }
    fun listar() : LiveData<List<Data>>
    {
        return db.cryptoDao().listar()
    }

    fun buscar(id: String): Data
    {
        return db.cryptoDao().buscar(id)
    }

    fun getCount(): Int{
        return db.cryptoDao().contar()
    }


}