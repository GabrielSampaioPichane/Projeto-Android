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


    //Varias que iram ser exibidas na interface do usuario
    var nome = ""
    var email = ""

    //Variaveis usadas na coleta dos dados para cadastro
    var emailCadastro = ""
    var nomeCadastro  = ""
    var senhaCadastro = ""


    //Captura dados atuais do usuario no banco de dados
    val idUsuario = Firebase.auth.currentUser?.uid.toString()
    //Acessa o estado do banco de dados
    val bancoDeDados = FirebaseFirestore.getInstance()

    //Desloga o usuario do Firebase
    fun deslogar(){
        FirebaseAuth.getInstance().signOut()
    }
     //Executa o cadastro do usuario no Firebase
     fun cadastroDadosUsuario( )  {

        //Lista para nome usuario
        val usuario = hashMapOf(
            "Nome" to nomeCadastro,
            "Email" to emailCadastro,
            "Senha" to senhaCadastro
        )

         //Salva dados no Firestore/firabase
        bancoDeDados.collection("banco_usuários").document(idUsuario).set(usuario).addOnSuccessListener {

            Log.d("bancdads", "Sucesso ao salvar")
        }.addOnFailureListener {
            Log.d("bancdads", "Falha ao salvar")
        }

    }

    //Autentica a entrada do usuario



    //Recebe os dados do banco de dados e exibe ao usuario
     fun atualizarDadosPerfilUsurio() = viewModelScope.launch{

         bancoDeDados.collection("banco_usuários").document(idUsuario).get().addOnSuccessListener { documentSnapshot ->

            if ( documentSnapshot != null) {

                nome = documentSnapshot.getString("Nome").toString()
                email  = documentSnapshot.getString("Email").toString()


                Log.d( "cd","Buscou dos dados")
            }

        }.addOnFailureListener { exception ->
            Log.d("bd", "falho em recuperar os dados ", exception)}

      }



}