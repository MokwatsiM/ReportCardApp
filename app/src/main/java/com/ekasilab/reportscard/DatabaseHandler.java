package com.ekasilab.reportscard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;

/**
 * Created by ekasilab on 06/10/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {


    //All static variables
    //Database Version
    private static final int DATABASE_VERSION = 4;

    //Database name
    private static final String DATABASE_NAME = "LEARNERDB";

    //Learner progress report table name

    private static final String LEARNER_TABLE = "LEARNER";

    //learner table columns names
    private static final String STUDENT_NO = "STDNO";
    private static final String STUDENT_NAME = "NAME";
    private static final String STUDENT_SURNAME = "SURNAME";
    private static final String ADDRESS = "ADDRESS";
    private static final String SUBJECT_NAME = "SUBJECT_NAME";

    private static final String NO_OF_SUBJECT = "NO_OF_SUBJECT";
    private static final String TEST1 = "TEST1";
    private static final String TEST2 = "TEST2";
    private static final String TEST3 = "TEST3";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

 //      onUpgrade(db, 1, DATABASE_VERSION);
        String createTable = "CREATE TABLE " + LEARNER_TABLE + "("
                + STUDENT_NO + " INTEGER PRIMARY KEY AUTOINCREMENT," + STUDENT_NAME + " TEXT,"
                + STUDENT_SURNAME + " TEXT," + ADDRESS + " TEXT,"
                + SUBJECT_NAME + " TEXT,"
                + TEST1 + " DOUBLE," + TEST2 + " DOUBLE,"
                + TEST3 + " DOUBLE" + ")";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //drop older table if existed

        db.execSQL("DROP TABLE IF EXISTS " + LEARNER_TABLE);
        onCreate(db);
    }

    //Add learner to db
    public void addLearner(LearnerReport learner) {

        //adding a new learner
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

//        String in = "insert into learner values("+learner.getStdNo()+","+learner.getName()+","+learner.getSurname()+","+learner.getLearnerAddress()+","+learner.getSubjectName()+","+
//                learner.getNoOfSubjects()+","+learner.getTest1()+","+learner.getTest2()+","+learner.getTest3()+")";

        values.put(STUDENT_NAME, learner.getName());
        values.put(STUDENT_SURNAME, learner.getSurname());
        values.put(ADDRESS, learner.getLearnerAddress());
        values.put(SUBJECT_NAME, learner.getSubjectName());

        values.put(TEST1, learner.getTest1());
        values.put(TEST2, learner.getTest2());
        values.put(TEST3, learner.getTest3());


        db.insert(LEARNER_TABLE, null, values);
        db.close();

    }

    //get single learner
    public LearnerReport getLearner(String name) throws SQLiteException {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(LEARNER_TABLE, new String[]{STUDENT_NO, STUDENT_NAME, STUDENT_SURNAME
                    , ADDRESS, SUBJECT_NAME, TEST1, TEST2, TEST3}, STUDENT_NAME + " like? ", new String[]
                    {name}, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }

            LearnerReport learner = new LearnerReport(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    Double.parseDouble(cursor.getString(5)),
                    Double.parseDouble(cursor.getString(6)), Double.parseDouble(cursor.getString(7)));
            return learner;
        } catch (SQLiteException ex) {
            throw new SQLiteException("Error while retrieving data\n" + ex.getMessage());
        }

    }


    //getting all learners
    public List<LearnerReport> getAllLearner() {

        List<LearnerReport> learnerList = new ArrayList<LearnerReport>();

        //Select all querry

        String selectQry = "SELECT * FROM " + LEARNER_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQry, null);

        //looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                LearnerReport learner;
                learner = new LearnerReport(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                        (cursor.getString(2)), cursor.getString(3), cursor.getString(4),
                        Double.parseDouble(cursor.getString(5)),
                        Double.parseDouble(cursor.getString(6)), Double.parseDouble(cursor.getString(7)));
                learnerList.add(learner);

            } while (cursor.moveToNext());
        }

        //return Learner list
        return learnerList;

    }

    //updating single contact
    public int updateLearner(LearnerReport learner) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NO, learner.getStdNo());
        values.put(STUDENT_NAME, learner.getName());
        values.put(STUDENT_SURNAME, learner.getSurname());
        values.put(ADDRESS, learner.getLearnerAddress());
        values.put(SUBJECT_NAME, learner.getSubjectName());

        values.put(TEST1, learner.getTest1());
        values.put(TEST2, learner.getTest2());
        values.put(TEST3, learner.getTest3());

        return db.update(LEARNER_TABLE, values, STUDENT_NO + " =  ?", new String[]{String.valueOf(learner.getStdNo())});


    }

    //deleting single contact
    public void deleteLearner(LearnerReport learner) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LEARNER_TABLE, STUDENT_NO + " = ?", new String[]{String.valueOf(learner.getStdNo())});
        db.close();
    }

    public int getLearnerCounts() {

        String counterQry = "SELECT * FROM " + LEARNER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(counterQry, null);

        //return count
        return cursor.getCount();
    }


}
