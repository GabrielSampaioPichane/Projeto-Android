package estudo.produto.presenter.presenter.ViewModel.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import estudo.produto.presenter.databinding.FragmentUsuarioBinding
import estudo.produto.presenter.presenter.LoginActivity
import estudo.produto.presenter.DadosRepositoryViewModels.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class UserFragment : Fragment() {
    private val viewModel: UserViewModel by viewModel()
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
        deslogar.setOnClickListener(){
            viewModel.deslogar()
         val intent = Intent (activity,LoginActivity::class.java)
            startActivity(intent)

        }
    }
    //carrega as principais funções
       override fun onStart() {
             viewModel.atualizarDadosPerfilUsurio()
             super.onStart()
           lifecycleScope.launch{ dadosAtualizados()}
       }

    //recolhe os dados do banco de dados pelo ViewModel e exibe
       private suspend fun dadosAtualizados(){
         delay(1000)
          nomeUser.text = viewModel.nome
          emailUser.text = viewModel.email

      }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
       return binding.root
    }


}