package estudo.produto.presenter.DadosRepositoryViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class HomeViewModel :ViewModel(){


    var url = ""

    fun urlWebView(){
         url =  "https://www.google.com"

    }

    }

