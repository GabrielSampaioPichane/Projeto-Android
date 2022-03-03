package estudo.produto.presenter.presenter.ViewModel.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.FragmentInitialBinding
import estudo.produto.presenter.presenter.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class InitialFragment : Fragment() {
   private lateinit var binding: FragmentInitialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentInitialBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

        //Verifica se o usuario atual/ultimo a fazer login está logado
        val accuser = Firebase.auth.currentUser?.uid
        if(accuser != null){
            lifecycleScope.launch{telaHome()}
        }else{
            lifecycleScope.launch { loginTela() }
        }

    }
    private suspend fun loginTela(){
        delay(2000)
        parentFragmentManager.beginTransaction().
        replace(R.id.login_container_fragments, LoginFragment()).commit()
    }
    private suspend fun telaHome(){
        delay(2000)
        val intent = Intent(activity, HomeActivity ::class.java)
        startActivity(intent)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
      return binding.root
    }


}