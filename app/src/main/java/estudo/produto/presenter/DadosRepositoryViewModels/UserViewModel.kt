package estudo.produto.presenter.DadosRepositoryViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel(){



    var nome = ""
    var email = ""

    var emailCadastro = ""
    var nomeCadastro  = ""
    var senhaCadastro = ""

    fun deslogar(){
        FirebaseAuth.getInstance().signOut()
    }

     fun cadastroDadosUsuario( )  {

        val bancoDeDados = FirebaseFirestore.getInstance()

        //Lista para nome usuario
        val usuario = hashMapOf(
            "Nome" to nomeCadastro,
            "Email" to emailCadastro,
            "Senha" to senhaCadastro
        )

        //Captura dados atuais do usuario no banco de dados
        val idUsuario = Firebase.auth.currentUser?.uid.toString()

        //Salva dados no Firestore/firabase
        bancoDeDados.collection("banco_usuários").document(idUsuario).set(usuario).addOnSuccessListener {

            Log.d("bancdads", "Sucesso ao salvar")
        }.addOnFailureListener {
            Log.d("bancdads", "Falha ao salvar")
        }

    }


     fun atualizarDadosPerfilUsurio() = viewModelScope.launch{

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