package estudo.produto.presenter.presenter.ViewModel.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import estudo.produto.presenter.databinding.FragmentNoticeBinding
import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NoticeFragment : Fragment() {
    private lateinit var binding: FragmentNoticeBinding
    private  val viewModel: HomeViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentNoticeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
         return binding.root
    }


            }

