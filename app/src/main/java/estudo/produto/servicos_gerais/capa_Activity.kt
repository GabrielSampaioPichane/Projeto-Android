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

import kotlinx.android.synthetic.main.activity_capa.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class capa_Activity : AppCompatActivity() {
    private lateinit var email_login : EditText
    private lateinit var senha_login : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capa)

        email_login = edit_login_email
        senha_login = edit_login_senha

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
                if (email_login.text.isEmpty() || senha_login.text.isEmpty()) {

                    val snackbar = Snackbar.make(
                        area_login,
                        "Favor informar dados de Login!",
                        Snackbar.LENGTH_LONG
                    )
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
            val barra_de_progresso = Widget_progressbar

            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                email_login.text.toString(),
                senha_login.text.toString()
            ).addOnCompleteListener() { task: Task<AuthResult> ->

                if(task.isSuccessful){
                barra_de_progresso.setVisibility(View.VISIBLE)
                  lifecycleScope.launch{tela_principal()}
                }
            }
         }

          private suspend fun tela_principal(){
                delay(1300)
              intent = Intent(this,inicial_Activity ::class.java)
             startActivity(intent)
              finish()
          }
}
