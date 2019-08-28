package xyz.codingabc.idioms.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import xyz.codingabc.idioms.data.dao.IdiomDao
import xyz.codingabc.idioms.data.util.ioThread
import xyz.codingabc.idioms.data.model.Idiom

@Database(entities = [Idiom::class], version = 1 , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun idiomDao(): IdiomDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it}
            }


        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "idioms_db"
            ).addCallback(object: Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    ioThread {
                        getInstance(context)
                            .idiomDao()
                            .insertData(PREPOPULATE_DATA)
                    }
                }
            }).build()

        /*fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.inMemoryDatabaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java
                    "idioms_database"
                ).build()
                    INSTANCE = instance
                instance
            }
        }*/

        val PREPOPULATE_DATA = listOf(
            Idiom("1", "some idiom text", "some idiom meaning"),
            Idiom("2", "some idiom text", "some idiom meaning"),
            Idiom("3", "some idiom text", "some idiom meaning"),
            Idiom("4", "some idiom text", "some idiom meaning"),
            Idiom("5", "some idiom text", "some idiom meaning"),
            Idiom("6", "some idiom text", "some idiom meaning"),
            Idiom("7", "some idiom text", "some idiom meaning"),
            Idiom("8", "some idiom text", "some idiom meaning"),
            Idiom("9", "some idiom text", "some idiom meaning"),
            Idiom("10", "some idiom text", "some idiom meaning")
        )
    }
}