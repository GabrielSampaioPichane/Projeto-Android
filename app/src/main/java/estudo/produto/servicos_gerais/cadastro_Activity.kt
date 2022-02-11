package estudo.produto.servicos_gerais

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap
import kotlin.concurrent.thread

class cadastro_Activity : AppCompatActivity() {

    private lateinit var emailUsuario: EditText
    private lateinit var senhaUsuario: EditText
    private lateinit var nomeUsuario: EditText
    private lateinit var cadastrar: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        //Podem ser acessador em qualquer função
        cadastrar = bt_cadastro
        emailUsuario = edit_useremail
        senhaUsuario = edit_usersenha
        nomeUsuario = edit_usernome
        auth = Firebase.auth

        //Volta para a tela de Login
        botao_voltar.setOnClickListener() {
            intent = Intent(this, capa_Activity::class.java)
            startActivity(intent)
        }

        //Efetua a area de cadastro
        cadastrar.setOnClickListener() {
            validar_campos()
        }
    }
    private val mensagem =arrayOf("Preencha todos os dados solicitados", "Cadastro realizado com sucesso")

    //Valida se todos os campos foram preenchidos
    private fun validar_campos(): Boolean {

        var error = false
        if (nomeUsuario.text.isEmpty() || emailUsuario.text.isEmpty() || senhaUsuario.text.isEmpty()) {

            val snackbar = Snackbar.make(area_cadastro, mensagem[0], Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint(Color.BLACK)
            snackbar.setTextColor(Color.WHITE)
            snackbar.show()
            error = true
        } else {
            cadastrar_usuario()
        }
        return error
    }
    //Cadastro no Firebase
    private fun cadastrar_usuario() {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            emailUsuario.text.toString(),
            senhaUsuario.text.toString()
        ).addOnCompleteListener() { task: Task<AuthResult> ->
            if (task.isSuccessful) {

                val snackbar = Snackbar.make(area_cadastro, mensagem[1], Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.BLACK)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
                dados_usuario()
               lifecycleScope.launch{timerbackslide()}
            } else {

                var falha: String = ""

                try {
                    task.getResult(FirebaseAuthException::class.java)

                } catch (e: FirebaseAuthUserCollisionException) {
                    falha = "Essa conta já foi cadastrada!"
                } catch (e: FirebaseAuthWeakPasswordException) {
                    falha = "Digite uma senha com no mínimo 6 caractéres!"
                } catch (e: FirebaseAuthInvalidCredentialsException) {
                    falha = "E-mail inválido!"
                } catch (e: Exception) {
                    falha = "Cadastro não efetuado"
                }
                val snackbar = Snackbar.make(area_cadastro, falha, Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.BLACK)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            }

        }
    }
   private suspend fun timerbackslide(){
            delay(1300)
            intent = Intent(this, capa_Activity::class.java)
            startActivity(intent)


    }

    private fun dados_usuario() {

            var nome  = nomeUsuario.text.toString()

            var bancdads = FirebaseFirestore.getInstance()

        var  pair : Pair <String, String> = Pair("Nome", nome)
             val usuario : Map <String, String> = mapOf(pair)
          var idusuario = Firebase.auth.currentUser.toString()

        bancdads.collection("banco_de_usuários").document(idusuario).set(usuario).addOnSuccessListener {

                Log.d("bancdads", "Sucesso ao salvar")
            }.addOnFailureListener() {
                Log.d("bancdads", "Falha ao salvar")
            }
        }
}


