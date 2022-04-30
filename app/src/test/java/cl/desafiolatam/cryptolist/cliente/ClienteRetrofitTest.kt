package cl.desafiolatam.cryptolist.cliente

import cl.desafiolatam.cryptolist.FileReader
import com.google.common.truth.Truth
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class ClienteRetrofitTest {

    private val server = MockWebServer()
    //colocamos en archivo jason que estpa en el equipo
    private  val body = FileReader.lectorJason("money.jason")

    @Before
    fun setUp() {
        //le voy al puerto de internet
        server.start(8080)
        //se conecta en forma de enqueue, luego error y me retorna el json
        server.enqueue(MockResponse().setResponseCode(200).setBody(body))
        //cuando nos coenctemos al retrofit le damos el resto
        server.url("moneda")

    }

    @After
    fun tearDown() {
        server.shutdown()
    }
    @Test
    fun test_apiSurccess(){
        val call = ClienteRetrofit.getInstancia("http://localhost:8080/").getMonedas()
        //hago una llamada no sincrona porque es una prueba
        var monedas = call.execute().body()
       // Truth.assertThat(monedas.size).isNotEqualTo(0)
    }


}