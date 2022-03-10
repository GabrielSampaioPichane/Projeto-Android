package estudo.produto.presenter.DadosRepositoryViewModels

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import estudo.produto.presenter.databinding.LayoutListaBinding
import kotlinx.coroutines.launch

class HomeViewModel :ViewModel(){



    var url1 = ""
    var url2 = ""
    var url3 = ""

    fun urlWebView() = viewModelScope.launch {
        url1 =  "https://g1.globo.com"
         url2 = "https://olhardigital.com.br"
         url3 = "https://www.bbc.com/portuguese/topics/cvjp2jr0k9rt"

    }

    }

