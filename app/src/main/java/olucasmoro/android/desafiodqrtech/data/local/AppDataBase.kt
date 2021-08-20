package olucasmoro.android.desafiodqrtech.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import olucasmoro.android.desafiodqrtech.data.local.model.*

@Database(entities = [Currency::class, Exchange::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao(): AppDao

    companion object {
        private const val DB_NAME = "datadb.db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}