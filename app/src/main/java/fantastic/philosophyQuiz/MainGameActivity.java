package fantastic.philosophyQuiz;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;
import fantastic.philosophyQuiz.activity.java.HomeScreenActivity;
import fantastic.philosophyQuiz.activity.java.PlayAgainActivity;
import fantastic.philosophyQuiz.objects.Question;
import info.hoang8f.widget.FButton;
import fantastic.philosophyQuiz.activity.kotlin.GameWonActivity;
import fantastic.philosophyQuiz.activity.kotlin.TimeUpActivity;
import fantastic.philosophyQuiz.interfaces.Activity;

public class MainGameActivity extends AppCompatActivity implements Activity {
    FButton buttonA, buttonB, buttonC, buttonD;
    TextView questionText, triviaQuizText, timeText, resultText, coinText;
    QuizHelper quizHelper;
    Question question;
    List<Question> list;
    int qid = 0;
    int timeValue = 21;
    int coinValue = 0;
    CountDownTimer countDownTimer;
    Typeface tb, sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);

        findByViewId();
        onListener();

        initFonts();
        initLocalDB();
        initTimer();

        //This method will set the que and four options
        updateQueAndOptions();
    }

    private void initLocalDB(){
        //Our database helper class
        quizHelper = new QuizHelper(this);
        //Make db writable
        quizHelper.getWritableDatabase();

        //It will check if the ques,options are already added in table or not
        //If they are not added then the getAllOfTheQuestions() will return a list of size zero
        if (quizHelper.getAllOfTheQuestions().size() == 0) {
            //If not added then add the ques,options in table
            quizHelper.allQuestion();
        }

        //This will return us a list of data type Question
        list = quizHelper.getAllOfTheQuestions();

        //Now we gonna shuffle the elements of the list so that we will get questions randomly
        Collections.shuffle(list);

        //currentQuestion will hold the que, 4 option and ans for particular id
        question = list.get(qid);

    }

    //Setting typefaces for textview and buttons - this will give stylish fonts on textview and button etc
    private void initFonts(){
        tb = Typeface.createFromAsset(getAssets(), "fonts/TitilliumWeb-Bold.ttf");
        sb = Typeface.createFromAsset(getAssets(), "fonts/shablagooital.ttf");
        triviaQuizText.setTypeface(sb);
        questionText.setTypeface(tb);
        buttonA.setTypeface(tb);
        buttonB.setTypeface(tb);
        buttonC.setTypeface(tb);
        buttonD.setTypeface(tb);
        timeText.setTypeface(tb);
        resultText.setTypeface(sb);
        coinText.setTypeface(tb);
    }

    //countDownTimer
    private void initTimer(){
        countDownTimer = new CountDownTimer(21000, 1000) {
            public void onTick(long millisUntilFinished) {

                //here you can have your logic to set text to timeText
                timeText.setText(String.valueOf(timeValue) + "\"");

                //With each iteration decrement the time by 1 sec
                timeValue -= 1;

                //This means the user is out of time so onFinished will called after this iteration
                if (timeValue == 0) {

                    //Since user is out of time setText as time up
                    resultText.setText(getString(R.string.timeup));

                    //Since user is out of time he won't be able to click any buttons
                    //therefore we will disable all four options buttons using this method
                    disableButton();
                }
            }
            //Now user is out of time
            public void onFinish() {
                //We will navigate him to the time up activity using below method
                timeUp();
            }
        }.start();
    }

    @Override
    public void findByViewId() {
        questionText = findViewById(R.id.triviaQuestion);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);
        triviaQuizText = findViewById(R.id.triviaQuizText);
        timeText = findViewById(R.id.timeText);
        resultText = findViewById(R.id.resultText);
        coinText = findViewById(R.id.coinText);
    }

    @Override
    public void onListener() {

    }

    public void updateQueAndOptions() {
        questionText.setText(question.getQuestion());
        buttonA.setText(question.getOptA());
        buttonB.setText(question.getOptB());
        buttonC.setText(question.getOptC());
        buttonD.setText(question.getOptD());

        timeValue = 20;

        //Now since the user has ans correct just reset timer back for another que- by cancel and start
        countDownTimer.cancel();
        countDownTimer.start();

        //set the value of coin text
        coinText.setText(String.valueOf(coinValue));

        coinValue++;
    }

    public void buttonA(View view) {
        if (question.getOptA().equals(question.getAnswer())) {
            buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            //Check if user has not exceeds the que limit
            if (qid < list.size() - 1) {
                //Now disable all the option button since user ans is correct so
                //user won't be able to press another option button after pressing one button
                disableButton();
                correctDialog();
            }
            //If user has exceeds the que limit just navigate him to GameWonActivity activity
            else {
                gameWon();
            }
        }
        //User ans is wrong then just navigate him to the PlayAgainActivity activity
        else {
            gameLostPlayAgain();
        }
    }

    public void buttonB(View view) {
        if (question.getOptB().equals(question.getAnswer())) {
            buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void buttonC(View view) {
        if (question.getOptC().equals(question.getAnswer())) {
            buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    public void buttonD(View view) {
        if (question.getOptD().equals(question.getAnswer())) {
            buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.lightGreen));
            if (qid < list.size() - 1) {
                disableButton();
                correctDialog();
            } else {
                gameWon();
            }
        } else {
            gameLostPlayAgain();
        }
    }

    //This method will navigate from current activity to GameWonActivity
    public void gameWon() {
        Intent intent = new Intent(this, GameWonActivity.class);
        startActivity(intent);
        finish();
    }

    //This method is called when user ans is wrong
    //this method will navigate user to the activity PlayAgainActivity
    public void gameLostPlayAgain() {
        Intent intent = new Intent(this, PlayAgainActivity.class);
        intent.putExtra("coin",String.valueOf(coinValue-1));
        startActivity(intent);
        finish();
    }

    //This method is called when time is up
    //this method will navigate user to the activity TimeUpActivity
    public void timeUp() {
        Intent intent = new Intent(this, TimeUpActivity.class);
        startActivity(intent);
        finish();
    }

    //If user press home button and come in the game from memory then this
    //method will continue the timer from the previous time it left
    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        countDownTimer.cancel();
    }

    //On BackPressed
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
        finish();
    }

    //This dialog is show to the user after he ans correct
    public void correctDialog() {
        final Dialog dialogCorrect = new Dialog(MainGameActivity.this);
        dialogCorrect.requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (dialogCorrect.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialogCorrect.getWindow().setBackgroundDrawable(colorDrawable);
        }

        dialogCorrect.setContentView(R.layout.dialog_correct);
        dialogCorrect.setCancelable(false);
        dialogCorrect.show();

        onPause();

        TextView correctText = dialogCorrect.findViewById(R.id.correctText);
        FButton buttonNext = dialogCorrect.findViewById(R.id.dialogNext);

        correctText.setTypeface(sb);
        buttonNext.setTypeface(sb);

        //OnCLick listener to go next que
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCorrect.dismiss();
                //it will increment the question number
                qid++;
                //get the que and 4 option and store in the currentQuestion
                question = list.get(qid);
                //Now this method will set the new que and 4 options
                updateQueAndOptions();
                //reset the color of buttons back to white
                resetColor();
                //Enable button - remember we had disable them when user ans was correct in there particular button methods
                enableButton();
            }
        });
    }

    //This method will make button color white again since our one button color was turned green
    public void resetColor() {
        buttonA.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.fbutton_default_color));
        buttonB.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.fbutton_default_color));
        buttonC.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.fbutton_default_color));
        buttonD.setButtonColor(ContextCompat.getColor(getApplicationContext(),R.color.fbutton_default_color));
    }

    public void disableButton() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
    }

    public void enableButton() {
        buttonA.setEnabled(true);
        buttonB.setEnabled(true);
        buttonC.setEnabled(true);
        buttonD.setEnabled(true);
    }
}
