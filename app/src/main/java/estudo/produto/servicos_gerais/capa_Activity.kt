package estudo.produto.servicos_gerais

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_cadastro.*

import kotlinx.android.synthetic.main.activity_capa.*

class capa_Activity : AppCompatActivity() {
    private lateinit var email_login : EditText
    private lateinit var senha_login : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capa)

        email_login = edit_login_email
        senha_login = edit_login_senha

        textView_texto_cadastro.setOnClickListener() {
            val intent = Intent(this, cadastro_Activity::class.java)
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
                }
                return error
            }


}
