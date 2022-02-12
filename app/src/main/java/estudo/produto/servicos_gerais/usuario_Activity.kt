package estudo.produto.servicos_gerais

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_usuario.*

class usuario_Activity : AppCompatActivity() {
    private lateinit var nomeUsuario : TextView
    private lateinit var emailUsuario: TextView
    private lateinit var deslogar : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        nomeUsuario = text_dados_nome
        emailUsuario = text_dados_email
        deslogar = bt_deslogar
        auth = Firebase.auth

        deslogar.setOnClickListener(){
            FirebaseAuth.getInstance().signOut()

            intent = Intent(this, capa_Activity::class.java)
            startActivity(intent)
        }
     }
  //Não está funcionando a recuperação dos dados do usuario
    override fun onStart() {
        super.onStart()
        var bancdads = FirebaseFirestore.getInstance()
        var idUsuario = Firebase.auth.currentUser.toString()

        bancdads.collection("banco_de_usuários").document(idUsuario)
            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null) {
                    nomeUsuario.text = snapshot.getString("Nome")
                    emailUsuario.text = snapshot.getString("Email")
                }
            }


    }
}

