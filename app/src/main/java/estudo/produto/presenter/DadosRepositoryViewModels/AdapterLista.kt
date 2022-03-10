package estudo.produto.presenter.DadosRepositoryViewModels

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import estudo.produto.presenter.R
import kotlinx.android.synthetic.main.layout_lista.view.*

class AdapterLista (val itens : MutableList <Dados>): RecyclerView.Adapter<AdapterLista.ListaViewHolder>(){


 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListaViewHolder {

        val listaItens = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_lista, parent, false)

        return ListaViewHolder(listaItens)
    }

 override fun onBindViewHolder(holder: ListaViewHolder, position: Int) {
       holder.bind(itens[position])
    }

 override fun getItemCount(): Int = itens.size

 inner class ListaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

 private  val textoLista = itemView.text_view_lista

         fun bind(item: Dados) {
           with(item){

               textoLista.text = dadosLista

           }
         }
     }
}