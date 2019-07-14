package fantastic.philosophyQuiz.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fantastic.philosophyQuiz.MainGameActivity;
import fantastic.philosophyQuiz.R;

public class PlayAgainActivity extends AppCompatActivity {

    Button playAgain;
    TextView wrongAnsText;
    Button returnButton;
    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_again);

        initResources();
        initListeners();
        setDataScore();
        initTypeFace();
    }

    private void initTypeFace(){
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
    public void onBackPressed() { finish(); }

    public void initResources() {
        playAgain = findViewById(R.id.playAgainButton);
        wrongAnsText = findViewById(R.id.wrongAns);
        returnButton = findViewById(R.id.retutn_button);
        score = findViewById(R.id.score);
    }

    public void initListeners() {
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
