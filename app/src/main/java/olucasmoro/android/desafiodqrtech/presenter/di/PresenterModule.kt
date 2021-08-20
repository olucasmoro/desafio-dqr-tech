package olucasmoro.android.desafiodqrtech.presenter.di

import olucasmoro.android.desafiodqrtech.presenter.iu.ConversorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<ConversorViewModel> { ConversorViewModel(get()) }
}

val presenterModules = listOf(viewModelModule)