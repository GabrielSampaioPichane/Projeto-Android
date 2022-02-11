package estudo.produto.servicos_gerais

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_inicial.*

class inicial_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        ic_perfil.setOnClickListener(){
            intent = Intent(this, usuario_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

}