package estudo.produto.servicos_gerais

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import estudo.produto.servicos_gerais.databinding.ActivityUsuarioBinding

class UsuarioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUsuarioBinding
    private lateinit var nomeUsuario : TextView
    private lateinit var emailUsuario: TextView
    private lateinit var deslogar : Button
    private lateinit var auth: FirebaseAuth
    private lateinit var voltar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding = ActivityUsuarioBinding.inflate(layoutInflater)
        nomeUsuario = binding.textDadosNome
        emailUsuario = binding.textDadosEmail
        deslogar = binding.btDeslogar
        voltar = binding.btVoltar
        auth = Firebase.auth


        deslogar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
           val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
           voltar.setOnClickListener {
             val intent = Intent (this, HomeActivity :: class.java)
           startActivity(intent)
           }

    }
  //Não está funcionando a recuperação dos dados do usuario
    override fun onStart() {
        super.onStart()

        val bancdads = FirebaseFirestore.getInstance()
        val idUsuario = Firebase.auth.currentUser?.uid.toString()


      bancdads.collection("banco_usuários").document(idUsuario).get()
          .addOnSuccessListener { documentSnapshot ->

            if ( documentSnapshot != null) {
                nomeUsuario.text = documentSnapshot.getString("Nome")
                emailUsuario.text = documentSnapshot.getString("Email")

                Log.d( "cd","Buscou dos dados")}

            }.addOnFailureListener { exception ->

              Log.d("bd", "falho em recuperar os dados ", exception)}



    }
}

