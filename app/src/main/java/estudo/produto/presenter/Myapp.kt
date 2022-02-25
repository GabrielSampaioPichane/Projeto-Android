package estudo.produto.presenter

import android.app.Application
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Myapp :Application () {
    override fun onCreate() {
        super.onCreate()

        startKoin(){
            androidLogger()
            androidContext(this@Myapp)

        }
    }
}