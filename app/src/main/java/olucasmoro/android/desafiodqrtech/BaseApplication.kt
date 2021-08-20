package olucasmoro.android.desafiodqrtech

import android.app.Application
import olucasmoro.android.desafiodqrtech.data.di.dataModules
import olucasmoro.android.desafiodqrtech.data.di.retrofitModule
import olucasmoro.android.desafiodqrtech.presenter.di.presenterModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(dataModules + retrofitModule + presenterModules)
        }
    }
}