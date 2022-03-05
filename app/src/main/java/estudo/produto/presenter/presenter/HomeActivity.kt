package estudo.produto.presenter.presenter

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import estudo.produto.presenter.R
import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import estudo.produto.presenter.databinding.ActivityHomeBinding
import estudo.produto.presenter.presenter.ViewModel.Fragments.NoticeFragment
import estudo.produto.presenter.presenter.ViewModel.Fragments.NãodefinidoFragment
import estudo.produto.presenter.presenter.ViewModel.Fragments.UserFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity() {
     private lateinit var binding: ActivityHomeBinding
     private lateinit var userFragment : UserFragment
     private lateinit var noticeFragment : NoticeFragment
     private lateinit var searchFragment : NãodefinidoFragment
     private  val viewModel : HomeViewModel by viewModel()
     private lateinit var perfil : TextView
     private lateinit var notice : TextView
     private lateinit var search : TextView
     override fun onCreate(savedInstanceState: Bundle?) {
         binding = ActivityHomeBinding.inflate(layoutInflater)
         super.onCreate(savedInstanceState)
         setContentView(binding.root)
         notice = binding.btNotice
         search = binding.btPesquisa
         perfil = binding.btPerfil

         searchFragment = NãodefinidoFragment()
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








