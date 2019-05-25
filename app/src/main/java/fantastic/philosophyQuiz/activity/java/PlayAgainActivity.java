package fantastic.philosophyQuiz.activity.java;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fantastic.philosophyQuiz.MainGameActivity;
import fantastic.philosophyQuiz.R;

public class PlayAgainActivity extends Activity implements fantastic.philosophyQuiz.interfaces.Activity {

    Button playAgain;
    TextView wrongAnsText;
    Button returnButton;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);

        findByViewId();
        setDataScore();
        onListener();

        //Setting typefaces for textview and button - this will give stylish fonts on textview and button
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playAgain.setTypeface(typeface);
        wrongAnsText.setTypeface(typeface);
    }

    private void setDataScore(){
        Intent intent = getIntent();
        String scoreIntent = intent.getStringExtra("coin");
        Log.d("coin",scoreIntent);
        score.setText(scoreIntent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void findByViewId() {
        playAgain = findViewById(R.id.playAgainButton);
        wrongAnsText = findViewById(R.id.wrongAns);
        returnButton = findViewById(R.id.retutn_button);
        score = findViewById(R.id.score);
    }

    @Override
    public void onListener() {
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayAgainActivity.this, MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                finish();
            }
        });
    }
}
