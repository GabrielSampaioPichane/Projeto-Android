package estudo.produto.servicos_gerais

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_capa.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class capa_Activity : AppCompatActivity() {
    private lateinit var emailLogin : EditText
    private lateinit var senhaLogin : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capa)

        emailLogin = edit_login_email
        senhaLogin = edit_login_senha

        textView_texto_cadastro.setOnClickListener() {
             intent = Intent(this, cadastro_Activity::class.java)
            startActivity(intent)
        }
        bt_entrar.setOnClickListener() {
            validar_campos_login()
        }
        }
        private fun validar_campos_login(): Boolean {

                var error = false
                if (emailLogin.text.isEmpty() || senhaLogin.text.isEmpty()) {

              val snackbar = Snackbar.make(area_login,"Favor informar dados de Login!",Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(Color.BLACK)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                    error = true
                }else{
                    login_usuarios()
                }
                return error
            }

        private fun login_usuarios() {
            val barraDeProgresso = Widget_progressbar

            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                emailLogin.text.toString(),
                senhaLogin.text.toString()
            ).addOnCompleteListener() { task: Task<AuthResult> ->

                if(task.isSuccessful){
                barraDeProgresso.setVisibility(View.VISIBLE)
                  lifecycleScope.launch{tela_principal()}
                }else{
                    try{
                        task.getResult(FirebaseAuthException::class.java)
                    }catch (e: Exception){
                        val snackbar = Snackbar.make(area_login,"Erro ao efetuar Login!",Snackbar.LENGTH_LONG)
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
        val actuser = Firebase.auth.currentUser?.uid
       if(actuser != null){
           lifecycleScope.launch{tela_principal()}
       }
    }
          private suspend fun tela_principal(){
                delay(1300)
              intent = Intent(this,inicial_Activity ::class.java)
             startActivity(intent)
              finish()
          }
}
