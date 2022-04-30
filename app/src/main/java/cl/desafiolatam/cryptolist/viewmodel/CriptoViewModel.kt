package cl.desafiolatam.cryptolist.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.desafiolatam.cryptolist.modelo.Crypto
import cl.desafiolatam.cryptolist.modelo.Data
import cl.desafiolatam.cryptolist.repositorio.ClienteRepository
import cl.desafiolatam.cryptolist.repositorio.CryptoRepositorio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CriptoViewModel(application: Application) : AndroidViewModel(application) {

    private val clienteRepo = ClienteRepository()

    //aqui me pide el contexto
    private val monedaRepo = CryptoRepositorio(getApplication())

    val critos = monedaRepo.listar()
    val dataDetalle = MutableLiveData<Data>()
    val filtro = MutableLiveData<String>()

    fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            if (monedaRepo.getCount() == 0) {
                clienteRepo.getListaMonedas().enqueue(object : Callback<Crypto> {
                    override fun onResponse(call: Call<Crypto>, response: Response<Crypto>) {
                        //si es que la llamada es correcta
                        response.body().let {
                            //ir objeto
                            monedaRepo.agregar(it!!.data)
                            // Log.d("ApiRespons", response.body().toString())
                        }

                    }

                    override fun onFailure(call: Call<Crypto>, t: Throwable) {
                        Log.e("CALL", t.message.toString())
                    }
                })
            }
        }

    }
        //me devuelve el detalle
//    fun updateCripto(dataDetalle: Data) {
//        //valores que tiene la data para que exiata el observer
//        //es value porque lo tengo en el mismo hilo
//        this.dataDetalle.value = dataDetalle
//    }

        fun updateCrypto(id: String) {
            CoroutineScope(Dispatchers.IO).launch {
                dataDetalle.postValue(monedaRepo.buscar(id))
            }
        }
}