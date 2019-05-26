package fantastic.philosophyQuiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import fantastic.philosophyQuiz.objects.Question;


class QuizHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "TQuiz.db";

    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 3;
    //Table name
    private static final String TABLE_NAME = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    QuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<Question> arraylist = new ArrayList<>();

        arraylist.add(new Question("С греческого языка слово «философия» переводится как:",
                "любовь к истине", "любовь к мудрости", "учение о мире", "божественная мудрость", "любовь к мудрости"));

        arraylist.add(new Question("Впервые употребил слово «философия» и назвал себя «философом»:",
                "Сократ", "Аристотель", "Пифагор", "Цицерон", "Пифагор"));

        arraylist.add(new Question("Определите время возникновения философии:",
                "середина III тыс-ия до н.э.", "VII-VI в.В. До н.Э.", "XVII-ХVIII в.в.", "V-XV в.в.", "VII-VI в.В. До н.Э."));

        arraylist.add(new Question("Основы бытия, проблемы познания, назначение человека и его положение в мире изучает:",
                "история", "философия", "онтология", "гносеология", "философия"));

        arraylist.add(new Question("Мировоззренческая форма общественного сознания, рационально обосновывающая предельные основания бытия, включая общество и право:",
                "социология", "история", "философия", "культурология", "философия"));

        arraylist.add(new Question("Направление, отрицающее существование Бога, называется:",
                "атеизм", "скептицизм", "агностицизм", "неотомизм", "атеизм"));

        arraylist.add(new Question("Раздел философии, в котором разрабатываются проблемы познания",
                "Эстетика", "Этика", "Онтология", "Гносеология", "Гносеология"));

        arraylist.add(new Question("Закон воздаяния в индийской религии и религиозной философии, определяющий характер нового рождения перевоплощения:",
                "мокша", "жэнь", "сансара", "карма", "карма"));

        arraylist.add(new Question("Понятие древнекитайской философии, обозначающее мужское, светлое и активное начало:",
                "Янь", "Карма", "Жэнь", "Дао", "Янь"));

        arraylist.add(new Question("Понятие древнекитайской философии, обозначающее женское, темное и пассивное начало:",
                "Инь", "Жэнь", "Прана", "Пуруша", "Инь"));

        arraylist.add(new Question("В индийской философии -- общая сумма совершенных поступков и их последствий, определяющая характер нового рождения",
                "Сансара", "Карма", "Нирвана", "Дхарма", "Карма"));

        arraylist.add(new Question("Китайский философ, основатель даосизма",
                "Конфуций", "Сюнь-цзы", "Лао-цзы", "Мэн-цзы", "Лао-цзы"));

        arraylist.add(new Question("Золотое правило нравственности: «Чего себе не пожелаешь, того не делай и другим» было впервые сформулировано:",
                "Сократом", "Конфуцием", "Протагором", "Буддой", "Конфуцием"));

        arraylist.add(new Question("Тезис, принадлежащий мыслителю Фалесу",
                "«Все течет»", "«В одну реку нельзя войти дважды»", "«Первооснова мира – огонь»", "«Начало всех вещей -- вода»", "«Начало всех вещей -- вода»"));

        arraylist.add(new Question("Имя основателя буддизма",
                "Патанджали", "Бадараяна", "Махавира", "Сидхартха", "Сидхартха"));

        arraylist.add(new Question("Имя основателя буддизма, означающее пробужденный, просветленный:",
                "Будда", "Лао-цзы", "Конфуций", "Нагарджуна", "Будда"));

        arraylist.add(new Question("Центральное понятие буддизма и джайнизма, означающее высшее состояние, цель человеческих стремлений:",
                "сансара", "жэнь", "дао", "нирвана", "нирвана"));

        arraylist.add(new Question("Положение: «Число есть сущность и смысл всего, что есть в мире», принадлежит:",
                "Протагору", "Пифагору", "Евклиду", "Аристотелю", "Пифагору"));

        arraylist.add(new Question("Движение, любое изменение являются лишь иллюзией чувственного мира, утверждали:",
                "пифагорейцы", "элеаты", "милетцы", "эпикурейцы" , "элеаты"));

        arraylist.add(new Question("«Я знаю, что ничего не знаю...». Автор афоризма:",
                "Фалес", "Эпикур", "Платон", "Сократ", "Сократ"));

        this.addAllQuestions(arraylist);
    }

    private void addAllQuestions(ArrayList<Question> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Question question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<Question> getAllOfTheQuestions() {

        List<Question> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            Question question = new Question();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
