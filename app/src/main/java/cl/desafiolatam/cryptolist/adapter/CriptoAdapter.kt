package cl.desafiolatam.cryptolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filter.FilterResults
import androidx.recyclerview.widget.RecyclerView
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.databinding.ItemLayoutBinding
import cl.desafiolatam.cryptolist.modelo.Data
import com.squareup.picasso.Picasso
import java.util.*

class CriptoAdapter:RecyclerView.Adapter<CriptoAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View, val listener: MiListener) : RecyclerView.ViewHolder(itemView)
    {
        private val binding = ItemLayoutBinding.bind(itemView)

        fun bindData(data: Data)
        {
            with(binding) {
              //elementos de item_layaut
                nombre.text = data.name
                txtValor.text = data.priceUsd
                var nombre: String
                 nombre=data.symbol!!.toString()
                Picasso.get().load(getImageFromSymbol(nombre)).resize(300,300).into(imagenMoneda)
                itemView.setOnClickListener{
                    listener.miOnclick(data)
                }
            }
        }
        fun getImageFromSymbol(symbol:String) = "https://static.coincap.io/assets/icons/"+symbol.lowercase(
            Locale.getDefault())+"@2x.png"

    }

    var lista: List<Data> = ArrayList()
    lateinit var listener: MiListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent,false)
        return CustomViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindData(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }


        fun updateData(cryto: List<Data>)
    {
        lista = cryto
        notifyDataSetChanged()
    }

    interface MiListener{
        fun miOnclick(data:Data)
    }

    fun setMilistener(listener:MiListener)
    {
        this.listener=listener
    }



}