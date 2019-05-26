package fantastic.philosophyQuiz.dataBase.dao

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import fantastic.philosophyQuiz.dataBase.entity.MyQuestion

@Dao
interface MyQuestionDao {

    @Query(value = "SELECT * FROM MyQuestions")
    fun getAllMyQuestions() : List<MyQuestion>

    @Insert(onConflict = REPLACE)
    fun insert(myQuestion: MyQuestion)

    @Query(value = "DELETE FROM MyQuestions WHERE id = :id")
    fun deleteMyQuestion(id : Long)

    @Query(value = "DELETE  FROM MyQuestions")
    fun deleteAllMyQuestions()
}