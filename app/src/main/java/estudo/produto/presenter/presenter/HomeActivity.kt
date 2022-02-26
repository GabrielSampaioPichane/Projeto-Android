package estudo.produto.presenter.presenter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.ActivityInicialBinding
import estudo.produto.presenter.databinding.FragmentUsuarioBinding
import estudo.produto.presenter.presenter.ViewModel.HomeViewModel
import estudo.produto.presenter.presenter.ViewModel.fragmentHome.UserFragment


class HomeActivity : AppCompatActivity() {
     private lateinit var binding: ActivityInicialBinding
     private lateinit var userFragment : UserFragment
     private lateinit var viewModel : HomeViewModel
     private lateinit var perfil : TextView
     override fun onCreate(savedInstanceState: Bundle?) {
         binding = ActivityInicialBinding.inflate(layoutInflater)
         super.onCreate(savedInstanceState)
         setContentView(binding.root)
         viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

         perfil = binding.btPerfil


         userFragment = UserFragment()

         perfil.setOnClickListener{
             setFragment(userFragment)
         }


    }

       private  fun setFragment(fragment: Fragment){
           val fragChange = supportFragmentManager.beginTransaction()
           fragChange.replace(R.id.container_fragment, fragment)
           fragChange.commit()
       }
    }








