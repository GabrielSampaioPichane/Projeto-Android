package estudo.produto.servicos_gerais

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import estudo.produto.servicos_gerais.databinding.ActivityCapaBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCapaBinding
    private lateinit var emailLogin : EditText
    private lateinit var senhaLogin : EditText
    private lateinit var cadastrar : TextView
    private lateinit var login : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCapaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        emailLogin = binding.editLoginEmail
        senhaLogin = binding.editLoginSenha
        cadastrar = binding.textViewTextoCadastro
         login = binding.btEntrar

        cadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
        login.setOnClickListener {
            validacampos()
        }
        }
        private fun validacampos(): Boolean {

                var error = false
                if (emailLogin.text.isEmpty() || senhaLogin.text.isEmpty()) {

              val snackbar = Snackbar.make(binding.areaLogin,"Favor informar dados de Login!",Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(Color.BLACK)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                    error = true
                }else{
                    loginUsuario()
                }
                return error
            }

        private fun loginUsuario() {
            val progressbar = binding.WidgetProgressbar

            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                emailLogin.text.toString(),
                senhaLogin.text.toString()
            ).addOnCompleteListener { task: Task<AuthResult> ->

                if(task.isSuccessful){
                    progressbar.visibility = View.VISIBLE
                  lifecycleScope.launch{telaHome()}
                }else{
                    try{
                        task.getResult(FirebaseAuthException::class.java)
                    }catch (e: Exception){
                        val snackbar = Snackbar.make(binding.areaLogin,"Erro ao efetuar Login!",Snackbar.LENGTH_LONG)
                        snackbar.setBackgroundTint(Color.BLACK)
                        snackbar.setTextColor(Color.WHITE)
                        snackbar.show()
                    }
                }
            }
         }

    override fun onStart() {
        super.onStart()
        //Verifica se o usuario atual/ultimo a fazer login está logado
        val accuser = Firebase.auth.currentUser?.uid
       if(accuser != null){
           lifecycleScope.launch{telaHome()}
       }
    }
          private suspend fun telaHome(){
                delay(1300)
             val intent = Intent(this,HomeActivity ::class.java)
             startActivity(intent)
              finish()
          }
}
