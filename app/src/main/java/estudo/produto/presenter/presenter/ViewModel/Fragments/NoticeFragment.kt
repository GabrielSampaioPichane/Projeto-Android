package estudo.produto.presenter.presenter.ViewModel.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavHost
import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.FragmentNewsCenterBinding
import estudo.produto.presenter.presenter.ViewModel.Fragments.NewsFragments.NoticiaEconomiaFragment
import estudo.produto.presenter.presenter.ViewModel.Fragments.NewsFragments.NoticiaTecnologiaFragment
import estudo.produto.presenter.presenter.ViewModel.Fragments.NewsFragments.NoticiasGeraisFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticeFragment : Fragment() {
    private lateinit var binding : FragmentNewsCenterBinding
    private lateinit var noticiaGerais : Button
    private lateinit var noticiaTecnologia : Button
    private lateinit var  noticiaEconomia : Button
    private  val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentNewsCenterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)


        noticiaGerais = binding.first
        noticiaTecnologia = binding.second
        noticiaEconomia = binding.third


        noticiaGerais.setOnClickListener{
            parentFragmentManager.beginTransaction().
            replace(R.id.container_fragment, NoticiasGeraisFragment()).addToBackStack(null).commit()

        }
        noticiaTecnologia.setOnClickListener{
            parentFragmentManager.beginTransaction().
            replace(R.id.container_fragment, NoticiaTecnologiaFragment()).addToBackStack(null).commit()
        }
        noticiaEconomia.setOnClickListener{
            parentFragmentManager.beginTransaction().
            replace(R.id.container_fragment, NoticiaEconomiaFragment()).addToBackStack(null).commit()
        }




       }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        return binding.root
    }
}