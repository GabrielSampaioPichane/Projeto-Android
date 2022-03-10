package estudo.produto.presenter.DadosRepositoryViewModels

data class Dados(

   var dadosLista : String
   )

class ItensBuilder {

   var dadosLista : String = ""

   fun build() : Dados = Dados (dadosLista)
}

fun dados (block : ItensBuilder.() -> Unit): Dados = ItensBuilder().apply(block).build()

fun attlista () = mutableListOf(dados {
   dadosLista = ""
})

