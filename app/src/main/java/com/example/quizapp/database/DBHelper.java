package com.example.quizapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.Score;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "QuizApp.db";
    private static final int DB_VERSION = 4;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Bảng Question
        db.execSQL("CREATE TABLE Question(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "question TEXT," +
                "optionA TEXT," +
                "optionB TEXT," +
                "optionC TEXT," +
                "optionD TEXT," +
                "answer TEXT," +
                "image TEXT," +
                "difficulty TEXT)");

        // Bảng Score
        db.execSQL("CREATE TABLE Score(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "score INTEGER," +
                "difficulty TEXT," +
                "date TEXT)");

        insertSampleQuestions(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Question");
        db.execSQL("DROP TABLE IF EXISTS Score");
        onCreate(db);
    }

    private void insertSampleQuestions(SQLiteDatabase db) {

        // ===== EASY =====
        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'Java là ngôn ngữ lập trình gì?'," +
                "'Hướng đối tượng'," +
                "'Đánh dấu'," +
                "'Cơ sở dữ liệu'," +
                "'Thiết kế đồ họa'," +
                "'A'," +
                "'img_java'," +
                "'easy')");

        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'Android là hệ điều hành dành cho thiết bị nào?'," +
                "'Máy in'," +
                "'Điện thoại và máy tính bảng'," +
                "'Tivi CRT'," +
                "'Máy scan'," +
                "'B'," +
                "'img_android'," +
                "'easy')");

        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'SQLite trong Android dùng để làm gì?'," +
                "'Lưu trữ dữ liệu'," +
                "'Thiết kế logo'," +
                "'Biên dịch app'," +
                "'Lưu video'," +
                "'A'," +
                "'img_sqlite'," +
                "'easy')");

        // ===== MEDIUM =====
        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'XML trong Android thường dùng để làm gì?'," +
                "'Viết giao diện'," +
                "'Chạy game 3D'," +
                "'Tạo video'," +
                "'Xóa database'," +
                "'A'," +
                "'img_xml'," +
                "'medium')");

        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'Android Studio dựa trên IDE nào?'," +
                "'Eclipse'," +
                "'IntelliJ IDEA'," +
                "'VS Code'," +
                "'NetBeans'," +
                "'B'," +
                "'img_studio'," +
                "'medium')");

        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'Thành phần nào dùng để chọn 1 đáp án trong nhiều đáp án trên Android?'," +
                "'TextView'," +
                "'RadioButton'," +
                "'ImageView'," +
                "'ProgressBar'," +
                "'B'," +
                "'img_android'," +
                "'medium')");

        // ===== HARD =====
        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'SQLiteOpenHelper dùng để làm gì?'," +
                "'Quản lý cơ sở dữ liệu SQLite'," +
                "'Hiển thị ảnh'," +
                "'Chạy Activity'," +
                "'Vẽ giao diện XML'," +
                "'A'," +
                "'img_sqlite'," +
                "'hard')");

        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'Phương thức nào được gọi khi Activity bắt đầu chạy?'," +
                "'onCreate()'," +
                "'onDraw()'," +
                "'onBind()'," +
                "'onDelete()'," +
                "'A'," +
                "'img_studio'," +
                "'hard')");

        db.execSQL("INSERT INTO Question(question,optionA,optionB,optionC,optionD,answer,image,difficulty) VALUES(" +
                "'Intent trong Android thường dùng để làm gì?'," +
                "'Chuyển màn hình / truyền dữ liệu'," +
                "'Tạo bảng SQLite'," +
                "'Thiết kế ảnh'," +
                "'Chạy SQL'," +
                "'A'," +
                "'img_android'," +
                "'hard')");
    }

    // ================= QUESTION =================
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Question", null);

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)
                );
                list.add(q);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    public ArrayList<Question> getQuestionsByDifficulty(String difficulty) {
        ArrayList<Question> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Question WHERE difficulty = ?",
                new String[]{difficulty}
        );

        if (cursor.moveToFirst()) {
            do {
                Question q = new Question(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)
                );
                list.add(q);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    // ================= SCORE =================
    public void insertScore(String name, int score, String difficulty, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "INSERT INTO Score(name,score,difficulty,date) VALUES(?,?,?,?)",
                new Object[]{name, score, difficulty, date}
        );
        db.close();
    }

    public ArrayList<Score> getAllScores() {
        ArrayList<Score> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM Score ORDER BY score DESC LIMIT 10",
                null
        );

        if (cursor.moveToFirst()) {
            do {
                Score s = new Score(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                list.add(s);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return list;
    }

    public void deleteAllScores() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Score");
        db.close();
    }
}