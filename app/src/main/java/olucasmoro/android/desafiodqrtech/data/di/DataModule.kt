package olucasmoro.android.desafiodqrtech.data.di

import olucasmoro.android.desafiodqrtech.data.local.AppDataBase
import olucasmoro.android.desafiodqrtech.data.local.source.AppLocalData
import olucasmoro.android.desafiodqrtech.data.local.source.AppLocalDataImpl
import olucasmoro.android.desafiodqrtech.data.network.source.AppRemoteData
import olucasmoro.android.desafiodqrtech.data.network.source.AppRemoteDataImpl
import olucasmoro.android.desafiodqrtech.domain.repository.AppRepositoryData
import olucasmoro.android.desafiodqrtech.domain.repository.AppRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val database = module {
    single { AppDataBase.getInstance(androidApplication()) }
    single { get<AppDataBase>().getDao() }
}

val localRemoteModule = module {
    factory<AppRemoteData> { AppRemoteDataImpl(apiService = get()) }
    factory<AppLocalData> { AppLocalDataImpl(appDao = get()) }
}

val repositoryModule = module {
    factory<AppRepositoryData> {
        AppRepositoryImpl(
            remoteData = get(),
            localData = get()
        )
    }
}

val dataModules = listOf(repositoryModule, localRemoteModule, database)