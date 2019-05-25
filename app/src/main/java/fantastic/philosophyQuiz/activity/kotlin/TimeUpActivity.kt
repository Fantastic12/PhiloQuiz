package fantastic.philosophyQuiz.activity.kotlin

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_time__up.*
import fantastic.philosophyQuiz.MainGameActivity
import fantastic.philosophyQuiz.R
import fantastic.philosophyQuiz.activity.java.HomeScreenActivity

class TimeUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time__up)

        onListener()

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        val typeface = Typeface.createFromAsset(assets, "fonts/shablagooital.ttf")
        timeUpText.typeface = typeface
        playAgainButton.typeface = typeface
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


    fun onListener() {
        playAgainButton.setOnClickListener {
            val intent = Intent(this@TimeUpActivity, MainGameActivity::class.java)
            startActivity(intent)
            finish()
        }

        retutn_button_time_up.setOnClickListener {
            startActivity(Intent(this@TimeUpActivity, HomeScreenActivity::class.java))
            finish()
        }
    }
}
