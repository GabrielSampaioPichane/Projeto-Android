package estudo.produto.presenter.presenter.ViewModel.fragmentHome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.FragmentUsuarioBinding
import estudo.produto.presenter.presenter.LoginActivity
import estudo.produto.presenter.presenter.ViewModel.HomeViewModel


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
      //  viewModel.atualizarDadosPerfilUsurio()

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_usuario, container, false)
    }


}