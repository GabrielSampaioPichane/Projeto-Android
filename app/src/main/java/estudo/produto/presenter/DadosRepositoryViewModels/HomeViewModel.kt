package estudo.produto.presenter.DadosRepositoryViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel :ViewModel(){
    var url = ""



    fun urlWebView() = viewModelScope.launch {
        url =  "https://www.google.com"
    }

    }

