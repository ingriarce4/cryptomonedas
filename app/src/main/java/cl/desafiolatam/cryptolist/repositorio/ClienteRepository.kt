package cl.desafiolatam.cryptolist.repositorio


import cl.desafiolatam.cryptolist.cliente.ClienteRetrofit
import cl.desafiolatam.cryptolist.modelo.Crypto
import retrofit2.Call

class ClienteRepository {

    private val cliente = ClienteRetrofit.getInstancia(ClienteRetrofit.BASE_URL)

    //TENEMOS UNA LLAMADA al service

    fun getListaMonedas(): Call<Crypto>
    {
        return cliente.getMonedas()
    }
}