package fantastic.philosophyQuiz.dataBase.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.text.Editable

@Entity(tableName = "MyQuestions")
data class MyQuestion(
        @PrimaryKey(autoGenerate = true) var id : Long?,
        @ColumnInfo(name = "Question") var question : String,
        @ColumnInfo(name = "Answer_A") var answerA : String,
        @ColumnInfo(name = "Answer_B") var answerB : String,
        @ColumnInfo(name = "Answer_C") var answerC : String,
        @ColumnInfo(name = "Answer_D") var answerD : String,
        @ColumnInfo(name = "CorrectAnswer") var correctAnswer : String
) {
    @Ignore
    constructor():this(null,"","","","","","")
}