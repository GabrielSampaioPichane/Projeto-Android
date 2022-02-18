package estudo.produto.servicos_gerais

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import estudo.produto.servicos_gerais.databinding.ActivityInicialBinding


class HomeActivity : AppCompatActivity() {
     private lateinit var binding: ActivityInicialBinding
     private lateinit var perfil : TextView

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicialBinding.inflate(layoutInflater)
        setContentView(binding.root)

         perfil = binding.imgPerfil

         perfil.setOnClickListener{
         var  intent = Intent(this, UsuarioActivity::class.java)
            startActivity(intent)
         }
    }

}