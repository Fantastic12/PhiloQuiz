package fantastic.philosophyQuiz.activity.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fantastic.philosophyQuiz.R
import fantastic.philosophyQuiz.dataBase.LocalDB
import fantastic.philosophyQuiz.dataBase.dao.MyQuestionDao
import fantastic.philosophyQuiz.dataBase.entity.MyQuestion
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_my_question.*
import android.view.View

class AddMyQuestionActivity : AppCompatActivity() {

    private var db : LocalDB? = null
    private var myQuestionDao : MyQuestionDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_my_question)

        db = LocalDB.getInstance(context = this)
        myQuestionDao = db?.myQuestionDao()

        initViews()

        add_question_button.setOnClickListener{
            clickAddButton()
            finish()
        }
    }

    private fun clickAddButton(){

        val answer : String? = "ANSWER"

        question.text.toString()
        answer_A.text.toString()
        answer_B.text.toString()
        answer_C.text.toString()
        answer_D.text.toString()
        val myQuestion = MyQuestion(1,
                question.text.toString(),
                answer_A.text.toString(),
                answer_B.text.toString(),
                answer_C.text.toString(),
                answer_D.text.toString(),
                answer.toString())
        Log.d("QUESTION", question.text.toString())
    }

    private fun addMyQuestionToLocalDB(myQuestion: MyQuestion) {
        Single.fromCallable {
            myQuestionDao?.insert(myQuestion)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    private fun initViews() {
        add_question_button.buttonColor = R.color.red
    }

    fun onCheckboxClicked(view: View) {
        when (view.id) {
            R.id.checkBoxA -> {
                checkBoxC.isChecked = false
                checkBoxB.isChecked = false
                checkBoxD.isChecked = false
            }
            R.id.checkBoxB -> {
                checkBoxA.isChecked = false
                checkBoxC.isChecked = false
                checkBoxD.isChecked = false
            }
            R.id.checkBoxC -> {
                checkBoxA.isChecked = false
                checkBoxB.isChecked = false
                checkBoxD.isChecked = false
            }
            R.id.checkBoxD -> {
                checkBoxA.isChecked = false
                checkBoxB.isChecked = false
                checkBoxC.isChecked = false
            }
        }
    }
}
