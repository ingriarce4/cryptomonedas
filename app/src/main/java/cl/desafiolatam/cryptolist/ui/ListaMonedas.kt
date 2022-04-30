package cl.desafiolatam.cryptolist.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import cl.desafiolatam.cryptolist.R
import cl.desafiolatam.cryptolist.adapter.CriptoAdapter
import cl.desafiolatam.cryptolist.databinding.FragmentListaMonedasBinding
import cl.desafiolatam.cryptolist.modelo.Data
import cl.desafiolatam.cryptolist.viewmodel.CriptoViewModel


class ListaMonedas : Fragment() {

   lateinit var binding:FragmentListaMonedasBinding
   private val viewModel by activityViewModels<CriptoViewModel>()
    private val adapter = CriptoAdapter()
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListaMonedasBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        val layaoutManager = LinearLayoutManager(requireContext())

        with(binding){

           txtUsuario.setOnEditorActionListener(OnEditorActionListener { textView, actionId, keyEvent ->
               if (actionId == EditorInfo.IME_ACTION_DONE || actionId === KeyEvent.ACTION_DOWN || actionId === KeyEvent.KEYCODE_ENTER) {
                   var usuario = txtUsuario.text.toString()
                   sharedPreferences = requireActivity().getSharedPreferences(usuario, Context.MODE_PRIVATE)
                   //DAMOS DATOTS
                   sharedPreferences.edit().putString(usuario, usuario).commit()
                   txtUsuario.isEnabled = false
                   return@OnEditorActionListener true
               }
               false
           })



            moneditas.adapter = adapter
            moneditas.layoutManager = layaoutManager

        }

        adapter.setMilistener(object :CriptoAdapter.MiListener{
            override fun miOnclick(data: Data) {
                viewModel.updateCrypto(data.id)
                Navigation.findNavController(requireView()).navigate(R.id.action_listaMonedas_to_detalleMoneda)
            }

        })

        //aqui se empieza a observar
        viewModel.critos.observe(viewLifecycleOwner, Observer {
            adapter.updateData(it)
        })

        viewModel.filtro.observe(viewLifecycleOwner){
            adapter.updateData(viewModel.critos.value!!.filter {
                    c-> c.name?.contains(it.toString(), true)!!
            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "CryptoMonedas"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filtro.value = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isEmpty())
                {
                    viewModel.filtro.value = ""
                }
                return false
            }
        })
    }



}