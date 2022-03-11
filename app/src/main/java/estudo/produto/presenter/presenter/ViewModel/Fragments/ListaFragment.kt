package estudo.produto.presenter.presenter.ViewModel.Fragments

import android.annotation.SuppressLint
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
import java.util.*

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

     val helper = androidx.recyclerview.widget.ItemTouchHelper (
         ListHelper(androidx.recyclerview.widget.ItemTouchHelper.UP or androidx.recyclerview.widget.ItemTouchHelper.DOWN,
             androidx.recyclerview.widget.ItemTouchHelper.LEFT or androidx.recyclerview.widget.ItemTouchHelper.RIGHT) )

         helper.attachToRecyclerView(listaCamp)
     }

    inner class ListHelper (dragDirs : Int, swipeDirs : Int): androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback(
        dragDirs, swipeDirs

    ){

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
              val from = viewHolder.adapterPosition
            val to = target.adapterPosition

       Collections.swap(adapter.itens, from, to)
       adapter.notifyItemMoved(from, to)

                return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
          adapter.itens.removeAt(viewHolder.adapterPosition)
            adapter.notifyItemRemoved(viewHolder.adapterPosition)


        }


    }


   @SuppressLint("NotifyDataSetChanged")
   private fun dadosListaAtualizado(){

        val textoDigitado = textCamp.text.toString()

       if (textoDigitado.isNotEmpty()){

           adapter.itens.add(dados {dadosLista = textoDigitado })

           adapter.notifyDataSetChanged()

           listaCamp.scrollToPosition(0)

           textCamp.text.clear()
       }


    }
    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        return binding.root
    }

}


