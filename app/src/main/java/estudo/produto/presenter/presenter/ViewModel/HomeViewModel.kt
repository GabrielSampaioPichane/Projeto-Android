package estudo.produto.presenter.presenter.ViewModel

import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import estudo.produto.presenter.databinding.FragmentUsuarioBinding

class HomeViewModel :ViewModel(){



    var nome = ""
    var email = ""

    fun deslogar(){
         FirebaseAuth.getInstance().signOut()
     }
      fun atualizarDadosPerfilUsurio() {

        val bancoDeDados = FirebaseFirestore.getInstance().collection("banco_usuários")
        val idUsuario = Firebase.auth.currentUser?.uid.toString()

        bancoDeDados.document(idUsuario).get().addOnSuccessListener { documentSnapshot ->

                if ( documentSnapshot != null) {

                      nome = documentSnapshot.getString("Nome").toString()
                     email  = documentSnapshot.getString("Email").toString()


                    Log.d( "cd","Buscou dos dados")
        }

        }.addOnFailureListener { exception ->
                Log.d("bd", "falho em recuperar os dados ", exception)}
    }

    }

