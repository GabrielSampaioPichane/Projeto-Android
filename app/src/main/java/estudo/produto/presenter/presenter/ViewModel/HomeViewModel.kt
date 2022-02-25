package estudo.produto.presenter.presenter.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import estudo.produto.presenter.databinding.FragmentUsuarioBinding

class HomeViewModel :ViewModel(){

    private lateinit var binding : FragmentUsuarioBinding


     /* O problema está aqui, o frangment não consegue acessar os dados, eu fiz uma estrutura para
        nao precisar usar todas essas linhas no proprio fragment, entao preciso de um help com a
        vinculação desses dados

    */

    fun deslogar(){
         FirebaseAuth.getInstance().signOut()
     }

      fun atualizarDadosPerfilUsurio() {

        val bancoDeDados = FirebaseFirestore.getInstance().collection("banco_usuários")
        val idUsuario = Firebase.auth.currentUser?.uid.toString()

        bancoDeDados.document(idUsuario).get().addOnSuccessListener { documentSnapshot ->

                if ( documentSnapshot != null) {

                  binding.textDadosNome.text  = documentSnapshot.getString("Nome")
                    binding.textDadosEmail.text  = documentSnapshot.getString("Email")

                    Log.d( "cd","Buscou dos dados")
                }

        }.addOnFailureListener { exception ->
                Log.d("bd", "falho em recuperar os dados ", exception)}
    }

    }

