package estudo.produto.presenter.presenter.ViewModel.Fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.FragmentLoginBinding
import estudo.produto.presenter.presenter.CadastroActivity
import estudo.produto.presenter.presenter.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
   private lateinit var binding: FragmentLoginBinding
    private lateinit var emailLogin : EditText
    private lateinit var senhaLogin : EditText
    private lateinit var cadastrar : TextView
    private lateinit var login : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        emailLogin = binding.editLoginEmail
        senhaLogin = binding.editLoginSenha
        cadastrar = binding.textViewTextoCadastro
        login = binding.btEntrar


        cadastrar.setOnClickListener {
            val intent = Intent(activity, CadastroActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            validacampos()
        }
    }

    private fun validacampos(): Boolean {

        var error = false
        if (emailLogin.text.isEmpty() || senhaLogin.text.isEmpty()) {

            val snackbar = Snackbar.make(binding.areaLogin,"Favor informar dados de LoginFragment!",
                Snackbar.LENGTH_LONG)
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
                  lifecycleScope.launch{
                      homeTransaction()
                  }


            }else{
                try{
                    task.getResult(FirebaseAuthException::class.java)
                }catch (e: Exception){
                    val snackbar = Snackbar.make(binding.areaLogin,"Erro ao efetuar LoginFragment!",Snackbar.LENGTH_LONG)
                    snackbar.setBackgroundTint(Color.BLACK)
                    snackbar.setTextColor(Color.WHITE)
                    snackbar.show()
                }
            }
        }
    }
      private suspend fun homeTransaction(){
         delay(1300)
          val intent = Intent (activity, HomeActivity::class.java)
          startActivity(intent)
        }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
         return binding.root
    }


}