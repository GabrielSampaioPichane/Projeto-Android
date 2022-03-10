package estudo.produto.presenter.presenter.ViewModel.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import estudo.produto.presenter.DadosRepositoryViewModels.*
import estudo.produto.presenter.databinding.FragmentNaodefinidoBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("UNUSED_EXPRESSION")
class ListaFragment : Fragment( ) {
   private val viewModel : HomeViewModel by viewModel()
    private lateinit var adiconar : FloatingActionButton
    private lateinit var textCamp : EditText
    private lateinit var listaCamp : RecyclerView
     private lateinit var binding : FragmentNaodefinidoBinding
     private  lateinit var adapter : AdapterLista

     override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentNaodefinidoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        textCamp = binding.textList

        listaCamp = binding.recycleView
        adiconar = binding.btAdicionar


     listaCamp.layoutManager = LinearLayoutManager(activity)

     adapter = AdapterLista(attlista())

     listaCamp.adapter = adapter

     adiconar.setOnClickListener {

      dadosListaAtualizado()

     }
  }

   private fun dadosListaAtualizado(){

        val textoDigitado = textCamp.text.toString()

       if (textoDigitado.isNotEmpty()){

           adapter.itens.add(dados {dadosLista = textoDigitado })

           adapter.notifyItemInserted(0)

           listaCamp.scrollToPosition(0)

       }


    }
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        return binding.root
    }

}


