package estudo.produto.presenter

import android.app.Application
import estudo.produto.presenter.InjectDemp.homeModule
import estudo.produto.presenter.InjectDemp.userModule
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

class Myapp : Application (){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Myapp)

            modules(homeModule, userModule)
        }

    }
}