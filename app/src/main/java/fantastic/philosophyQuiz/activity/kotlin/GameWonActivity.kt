package fantastic.philosophyQuiz.activity.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import fantastic.philosophyQuiz.MainGameActivity
import fantastic.philosophyQuiz.R
import kotlinx.android.synthetic.main.game_won.*

class GameWonActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_won)

        playAgain()
    }

    private fun playAgain() {
        playAgainButton1.setOnClickListener {
            val intent = Intent(this@GameWonActivity, MainGameActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
