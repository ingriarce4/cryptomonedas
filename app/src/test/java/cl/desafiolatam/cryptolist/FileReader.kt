package cl.desafiolatam.cryptolist

import java.io.File
import java.io.InputStreamReader


//todooo es estático
object FileReader {

fun lectorJason(archivos:String): String{

    //construyo
    val input = File("src/main/assets/$archivos").inputStream()
    val builder = StringBuilder()
    val lector = InputStreamReader(input, "Utf-8")
    lector.readLines().forEach {
        // en cada vuelta el me da cada linea y por cada iteracion dentro del landa me da cada linea
        builder.append(it)
    }
    return builder.toString()
  }
}