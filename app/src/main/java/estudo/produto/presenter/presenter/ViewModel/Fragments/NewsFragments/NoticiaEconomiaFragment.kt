package estudo.produto.presenter.presenter.ViewModel.Fragments.NewsFragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import estudo.produto.presenter.R
import estudo.produto.presenter.databinding.FragmentThirdNewsBinding
import estudo.produto.presenter.presenter.ViewModel.Fragments.NoticeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticiaEconomiaFragment : Fragment() {
    private  val viewModel: HomeViewModel by viewModel()
    private lateinit var centralNotice : NoticeFragment
    private lateinit var binding : FragmentThirdNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding =FragmentThirdNewsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        centralNotice = NoticeFragment()
        viewModel.urlWebView()
        /*detecta os eventos de click do botao de voltar do celular, e volta as
        e volta caso tenha permissão
        * */


        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.webview3.canGoBack()){
                    binding.webview3.goBack()
                }else{
                    isEnabled = false
                    activity?.onBackPressed()
                }
                 }
        })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onStart() {
        super.onStart()
        //navegação pelo google
        binding.webview3.apply {
            settings.safeBrowsingEnabled = true
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(viewModel.url3)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root

    }
}