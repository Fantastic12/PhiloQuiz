package fantastic.philosophyQuiz.dataBase

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import fantastic.philosophyQuiz.dataBase.dao.MyQuestionDao
import fantastic.philosophyQuiz.dataBase.entity.MyQuestion

@Database(entities = [MyQuestion::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {

    abstract fun myQuestionDao() : MyQuestionDao

    companion object{
        private var INSTANCE : LocalDB? = null

        fun getInstance(context : Context) : LocalDB? {
            if(INSTANCE == null){
                synchronized(LocalDB::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            LocalDB::class.java, "local.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }

}