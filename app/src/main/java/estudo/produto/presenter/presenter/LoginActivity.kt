package estudo.produto.presenter.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import estudo.produto.presenter.DadosRepositoryViewModels.UserViewModel
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.EnterActivityBinding
import estudo.produto.presenter.presenter.ViewModel.Fragments.InitialFragment
import estudo.produto.presenter.presenter.ViewModel.Fragments.LoginFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {
    private val viewModel : UserViewModel by viewModel()
    private lateinit var binding : EnterActivityBinding

    private lateinit var initialFrag : InitialFragment
    private lateinit var loginFrag : LoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
      binding = EnterActivityBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

           initialFrag = InitialFragment()

        setFragment(initialFrag)


        }
    private  fun setFragment(fragment: Fragment){
        val fragChange = supportFragmentManager.beginTransaction()
        fragChange.replace(R.id.login_container_fragments, fragment)
        fragChange.commit()
    }




}
