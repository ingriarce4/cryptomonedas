package cl.desafiolatam.cryptolist.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cl.desafiolatam.cryptolist.modelo.Data

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun agregarCrypto(datalist:List<Data>)

    @Query("select id, changePercent24Hr,marketCapUsd, maxSupply,name," +
            "priceUsd,rank,supply,symbol,volumeUsd24Hr,vwap24Hr from monedas")

    fun listar() : LiveData<List<Data>>


    @Query("select id,changePercent24Hr,marketCapUsd, maxSupply,name," +
            "priceUsd,rank,supply,symbol,volumeUsd24Hr,vwap24Hr from monedas " +
            "where id = :id")

    fun buscar(id:String) : Data

    @Query("select count(*) from monedas")
    fun contar(): Int


}