package estudo.produto.presenter.InjectDemp

import estudo.produto.presenter.DadosRepositoryViewModels.HomeViewModel
import estudo.produto.presenter.DadosRepositoryViewModels.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel()
    }
}
val userModule = module {

    viewModel{
        UserViewModel()
    }

}