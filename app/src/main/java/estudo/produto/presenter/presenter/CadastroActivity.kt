package estudo.produto.presenter.presenter

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import estudo.produto.presenter.DadosRepositoryViewModels.UserViewModel
import estudo.produto.presenter.databinding.ActivityCadastroBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastroActivity : AppCompatActivity() {
    private val viewModel : UserViewModel by viewModel()
   private lateinit var binding: ActivityCadastroBinding
    private lateinit var emailUsuario: EditText
    private lateinit var senhaUsuario: EditText
    private lateinit var nomeUsuario: EditText
    private lateinit var cadastrar: Button
    private lateinit var auth: FirebaseAuth
   private lateinit var voltar : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        voltar = binding.botaoVoltar
        cadastrar = binding.btCadastro
        emailUsuario = binding.editUseremail
        senhaUsuario = binding.editUsersenha
        nomeUsuario = binding.editUsernome
        auth = Firebase.auth

        //Efetua a area de cadastro
        cadastrar.setOnClickListener {
            validarCampos()
        }
    }
    private val mensagem =arrayOf("Preencha todos os dados solicitados", "Cadastro realizado com sucesso")

    //Valida se todos os campos foram preenchidos
    private fun validarCampos(): Boolean {

        var error = false
        if (nomeUsuario.text.isEmpty() || emailUsuario.text.isEmpty() || senhaUsuario.text.isEmpty()) {

            val snackbar = Snackbar.make(binding.areaCadastro, mensagem[0], Snackbar.LENGTH_LONG)
            snackbar.setBackgroundTint(Color.BLACK)
            snackbar.setTextColor(Color.WHITE)
            snackbar.show()
            error = true

        } else {
            cadastrarUsuario()
        }
        return error
    }
    //Cadastro no Firebase
    private fun cadastrarUsuario() {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            emailUsuario.text.toString(),
            senhaUsuario.text.toString()
        ).addOnCompleteListener { task: Task<AuthResult> ->
            if (task.isSuccessful) {

                val snackbar = Snackbar.make(binding.areaCadastro, mensagem[1], Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.BLACK)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
               viewModel.cadastroDadosUsuario()
                envioDados()

               lifecycleScope.launch{timerBackslide()}
            } else {

                var falha = ""

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
                val snackbar = Snackbar.make(binding.areaCadastro, falha, Snackbar.LENGTH_LONG)
                snackbar.setBackgroundTint(Color.BLACK)
                snackbar.setTextColor(Color.WHITE)
                snackbar.show()
            }

        }
    }
   private suspend fun timerBackslide(){
            delay(1300)
          val  intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
       finish()
    }

   private fun envioDados( ) {
     viewModel.emailCadastro = emailUsuario.text.toString()
     viewModel.senhaCadastro =  senhaUsuario.text.toString()
     viewModel.nomeCadastro  = nomeUsuario.text.toString()
   }

}

