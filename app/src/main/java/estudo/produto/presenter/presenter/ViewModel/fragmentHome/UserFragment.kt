package estudo.produto.presenter.presenter.ViewModel.fragmentHome

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.FragmentUsuarioBinding
import estudo.produto.presenter.presenter.LoginActivity
import estudo.produto.presenter.presenter.ViewModel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get


class UserFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentUsuarioBinding
    private lateinit var nomeUser : TextView
    private lateinit var emailUser : TextView
    private lateinit var deslogar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
     binding = FragmentUsuarioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        nomeUser = binding.textDadosNome
        emailUser = binding.textDadosEmail
        deslogar = binding.btDeslogar
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        deslogar.setOnClickListener(){
            viewModel.deslogar()
         val intent = Intent (activity,LoginActivity::class.java)
            startActivity(intent)

        }
    }


       override fun onStart() {
           super.onStart()
           viewModel.atualizarDadosPerfilUsurio()
           lifecycleScope.launch{ dadosAtualizados()}

       }


       private suspend fun dadosAtualizados(){
         delay(1180)
          nomeUser.text = viewModel.nome
          emailUser.text = viewModel.email

      }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
       return binding.root
    }


}