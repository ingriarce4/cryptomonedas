package cl.desafiolatam.cryptolist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.databinding.FragmentDetalleMonedaBinding
import cl.desafiolatam.cryptolist.viewmodel.CriptoViewModel
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class DetalleMoneda : Fragment() {

    lateinit var binding: FragmentDetalleMonedaBinding
    private val viewModel by activityViewModels<CriptoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetalleMonedaBinding.inflate(layoutInflater)
        viewModel.dataDetalle.observe(viewLifecycleOwner, Observer {
            with(binding)
            {
                textView8.text = it.symbol
                textView3.text = it.name
                //tvHora.text = it.changePercent24Hr
                val sdf = SimpleDateFormat("hh:mm:ss")

                val formato = DecimalFormat("#.00")
                var supplyy = it.supply.toString().toDouble()
                textView13.text = formato.format(supplyy).toString()

                var marketCap = it.marketCapUsd.toString().toDouble()
                textView14.text = formato.format(marketCap).toString()

                var dolare = it.priceUsd.toString().toDouble()
                textView7.text = formato.format(dolare).toString()

                Picasso.get().load(getImageFromSymbol(it.symbol.toString())).into(imgDetalle)

            }


        })

        return binding.root
    }
    fun getImageFromSymbol(symbol:String) = "https://static.coincap.io/assets/icons/"+symbol.lowercase(
        Locale.getDefault())+"@2x.png"

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}