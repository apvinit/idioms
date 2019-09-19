package xyz.codingabc.idioms.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import xyz.codingabc.idioms.data.dao.*
import xyz.codingabc.idioms.data.model.*
import java.io.FileOutputStream
import java.io.IOException

@Database(
    entities = [Antonym::class, Synonym::class, OneWordSubstitution::class, Idiom::class, Preposition::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun idiomDao(): IdiomDao
    abstract fun antonymDao(): AntonymDao
    abstract fun synonymDao(): SynonymDao
    abstract fun oneWordSubstitutionDao(): OneWordSubstitutionDao
    abstract fun prepositionDao(): PrepositionDao

    companion object {

        private const val DATABASE_NAME = "app.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }


        private fun buildDatabase(context: Context): AppDatabase {

            copyAttachedDatabase(context)

            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).addMigrations(object : Migration(1, 2) {
                override fun migrate(database: SupportSQLiteDatabase) {
                    // do something with migration here
                }
            }).build()
        }

        @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        private fun copyAttachedDatabase(context: Context) {
            val dbPath = context.getDatabasePath(DATABASE_NAME)

            // If the database already exists, return
            if (dbPath.exists()) return

            // make sure we have a path to the file
            dbPath.parentFile.mkdirs()

            // try to copy the file
            try {
                val inputStream = context.assets.open("databases/$DATABASE_NAME")
                val output = FileOutputStream(dbPath)

                inputStream.copyTo(output, 8192)
                inputStream.close()

                output.flush()
                output.close()
            } catch (e: IOException) {
                Log.d("AppDatabase", "Failed to open file", e)
            }
        }
    }
}
