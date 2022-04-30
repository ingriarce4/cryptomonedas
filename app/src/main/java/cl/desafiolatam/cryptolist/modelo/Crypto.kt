package cl.desafiolatam.cryptolist.modelo


import com.google.gson.annotations.SerializedName

data class Crypto(
    @SerializedName("data")
    val `data`: List<Data>
)