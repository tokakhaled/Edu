package com.example.edu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {
    public static final String db_name = "car_db";
    public static final int db_version = 1;
    public static final String EDU_DB_NAME = "education";
    public static final String QU_CLN_ID = "id";
    public static final String QU_CLN = "question";
    public static final String ANSWER_CLN = "answer";
    private static final String TAG = "MyDataBase";

    public MyDataBase(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EDU_DB_NAME + " (" + QU_CLN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                QU_CLN + " TEXT , " + ANSWER_CLN + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS education");
        onCreate(db);

    }

    public void insert(ArrayList<String> selQuestion, ArrayList<String> selAnswer) {

        int size = selQuestion.size();

        // Check that both lists have the same size
        if (size != selAnswer.size()) {
            Log.i(TAG, "questions sizes not equal to answers size");
            throw new IllegalArgumentException();
            // Or some more elegant way to handle this error condition
        }

        SQLiteDatabase db = getWritableDatabase();

        try {
            for (int i = 0; i < size; ++i) {
                ContentValues cv = new ContentValues();
                cv.put(QU_CLN, selQuestion.get(i));
                cv.put(ANSWER_CLN, selAnswer.get(i));
                db.insertOrThrow(EDU_DB_NAME, null, cv);
            }
            db.close();
        } catch (Exception e) {
            Log.e(TAG, e + " ");
        }
    }


    public List<String> getAnswer() {
        List<String> answers = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + ANSWER_CLN + " FROM " + EDU_DB_NAME, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            answers.add(cursor.getString(cursor.getColumnIndex(ANSWER_CLN)));
            Log.d(TAG, answers.toString());

            cursor.moveToNext();
        }
        cursor.close();
        return answers;

    }

//    public ArrayList<String> getAllCotactsEmail() {
//        ArrayList<String> arrayList = new ArrayList<>();
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor res = db.rawQuery("select * from contacts", null);
//        res.moveToFirst();
//
//        if (res != null) {
//            while (res.isAfterLast() == false) {
//                arrayList.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
//                Log.d("emailssinlisttt", arrayList.toString());
//
//                res.moveToNext();
//            }
//        }
//        return arrayList;
//
//    }
}
