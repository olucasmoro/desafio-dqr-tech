package olucasmoro.android.desafiodqrtech.presenter.di

import olucasmoro.android.desafiodqrtech.presenter.iu.ConvertViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<ConvertViewModel> { ConvertViewModel(get()) }
}

val presenterModules = listOf(viewModelModule)