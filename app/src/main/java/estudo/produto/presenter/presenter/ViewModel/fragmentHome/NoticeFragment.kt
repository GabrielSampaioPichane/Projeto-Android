package estudo.produto.presenter.presenter.ViewModel.fragmentHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.FragmentNoticeBinding
import estudo.produto.presenter.presenter.ViewModel.HomeViewModel


class NoticeFragment : Fragment() {
    private lateinit var binding: FragmentNoticeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentNoticeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
         return binding.root
    }


            }

