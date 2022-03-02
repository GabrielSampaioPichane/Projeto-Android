package estudo.produto.presenter.presenter.ViewModel.fragmentHome

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import estudo.produto.presenter.databinding.FragmentSearchBinding
import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private  val viewModel: HomeViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        viewModel.urlWebView()

        /*detecta os eventos de click do botao de voltar do celular, e volta as
        e volta caso tenha permissão
        * */
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(binding.webview.canGoBack()){
                   binding.webview.goBack()
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
        binding.webview.apply {

            settings.safeBrowsingEnabled = true
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            loadUrl(viewModel.url)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return binding.root
    }




}