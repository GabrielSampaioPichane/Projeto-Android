package estudo.produto.presenter.presenter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.ActivityInicialBinding
import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import estudo.produto.presenter.presenter.ViewModel.fragmentHome.NoticeFragment
import estudo.produto.presenter.presenter.ViewModel.fragmentHome.SearchFragment
import estudo.produto.presenter.presenter.ViewModel.fragmentHome.UserFragment


class HomeActivity : AppCompatActivity() {
     private lateinit var binding: ActivityInicialBinding
     private lateinit var userFragment : UserFragment
     private lateinit var noticeFragment : NoticeFragment
     private lateinit var searchFragment : SearchFragment
     private lateinit var viewModel : HomeViewModel
     private lateinit var perfil : TextView
     private lateinit var notice : TextView
     private lateinit var search : TextView
     override fun onCreate(savedInstanceState: Bundle?) {
         binding = ActivityInicialBinding.inflate(layoutInflater)
         super.onCreate(savedInstanceState)
         setContentView(binding.root)
         viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
         notice = binding.btNotice
         search = binding.btBusca
         perfil = binding.btPerfil

         searchFragment = SearchFragment()
         noticeFragment = NoticeFragment()
         userFragment = UserFragment()

         changeFragmentOnClick ()

        setFragment(noticeFragment)

    }

       private  fun setFragment(fragment: Fragment){
           val fragChange = supportFragmentManager.beginTransaction()
           fragChange.replace(R.id.container_fragment, fragment)
           fragChange.commit()
       }

      private fun changeFragmentOnClick () {

          perfil.setOnClickListener{
              setFragment(userFragment)
          }

        notice.setOnClickListener{
            setFragment(noticeFragment)
        }

           search.setOnClickListener{
               setFragment(searchFragment)
           }

      }


}








