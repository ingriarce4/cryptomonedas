package cl.desafiolatam.cryptolist.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.databinding.FragmentAnimacionBinding


class Animacion : Fragment() {

    lateinit var binding: FragmentAnimacionBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimacionBinding.inflate(inflater, container, false)

        Handler(Looper.getMainLooper()).postDelayed({
            Navigation.findNavController(requireView())
                .navigate(R.id.action_animacion_to_listaMonedas)
        }, 5000)
        return binding.root
    }
}