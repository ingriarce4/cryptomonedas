package cl.desafiolatam.cryptolist.servicio

import cl.desafiolatam.cryptolist.modelo.Crypto
import retrofit2.Call
import retrofit2.http.GET

//2.- Lacemos el servicio con la funcion que hace llamada al API
interface CryptoService {
    @GET("assets")
    fun getMonedas():Call<Crypto>
}