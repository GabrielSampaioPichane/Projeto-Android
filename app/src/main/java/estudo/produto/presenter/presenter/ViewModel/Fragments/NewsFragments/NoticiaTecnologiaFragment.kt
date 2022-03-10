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
import androidx.navigation.fragment.findNavController
import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import estudo.produto.presenter.databinding.FragmentSecondNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoticiaTecnologiaFragment : Fragment() {
    private  val viewModel: HomeViewModel by viewModel()
    private lateinit var binding : FragmentSecondNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentSecondNewsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        viewModel.urlWebView()
        /*detecta os eventos de click do botao de voltar do celular, e volta as
        e volta caso tenha permissão
        * */


        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (binding.webview2.canGoBack()) {
                    binding.webview2.goBack()
                } else {
                    isEnabled = false
                    activity?.onBackPressed()
                }
                //findNavController().navigateUp()
            }

             })
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onStart() {
        super.onStart()
        //navegação pelo google
        binding.webview2.apply {
            settings.safeBrowsingEnabled = true
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(viewModel.url2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root

    }
}