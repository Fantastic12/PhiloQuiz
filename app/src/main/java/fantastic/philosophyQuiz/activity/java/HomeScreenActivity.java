package fantastic.philosophyQuiz.activity.java;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fantastic.philosophyQuiz.activity.kotlin.AddMyQuestionActivity;
import info.hoang8f.widget.FButton;
import fantastic.philosophyQuiz.MainGameActivity;
import fantastic.philosophyQuiz.R;
import fantastic.philosophyQuiz.interfaces.Activity;
import fantastic.philosophyQuiz.utils.LocaleHelper;

public class HomeScreenActivity extends AppCompatActivity implements Activity {

    FButton playGame,score,addQuestion,quit;
    TextView tQ;
    ImageView changeLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        findByViewId();
        initViews();
        onListener();
    }

    //Typeface - this is for fonts style
    private void initViews() {
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/shablagooital.ttf");
        playGame.setTypeface(typeface);
        quit.setTypeface(typeface);
        tQ.setTypeface(typeface);
        score.setTypeface(typeface);
        addQuestion.setTypeface(typeface);

        score.setButtonColor(R.color.red);
        playGame.setButtonColor(R.color.red);
        quit.setButtonColor(R.color.red);
        addQuestion.setButtonColor(R.color.red);
    }

    @Override
    public void findByViewId() {
        playGame = findViewById(R.id.playGame);
        quit =  findViewById(R.id.quit);
        tQ = findViewById(R.id.tQ);
        changeLanguage = findViewById(R.id.changeLanguage);
        addQuestion = findViewById(R.id.add_question_button);
        score = findViewById(R.id.score_button);
    }

    @Override
    public void onListener() {
        playGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, MainGameActivity.class);
                startActivity(intent);
                finish();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String language = LocaleHelper.getLanguage(HomeScreenActivity.this);

                if(language.equals("ru")){
                    LocaleHelper.setLocale(HomeScreenActivity.this, "en");
                    recreate();
                }else if (language.equals("en")){
                    //Change Application level locale
                    LocaleHelper.setLocale(HomeScreenActivity.this, "ru");
                    //It is required to recreate the activity to reflect the change in UI.
                    recreate();
                }else {
                    LocaleHelper.setLocale(HomeScreenActivity.this, "ua");
                    //It is required to recreate the activity to reflect the change in UI.
                    recreate();
                }
            }
        });
        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddMyQuestionActivity.class));
            }
        });
    }


}
