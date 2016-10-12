package com.ekasilab.reportscard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ekasilab on 12/10/2016.
 */

public class DatabaseHandlerSubject extends SQLiteOpenHelper {


    //All static variables
    //Database Version
    private static final int DATABASE_VERSION = 3;

    //Database name
    private static final String DATABASE_NAME = "LEARNERDB";

    //Learner progress report table name

    private static final String SUBJECT_TABLE = "Subject";

    //Subject table columns
    private static final String SUBJECT_ID = "SUBJECT_ID";
    private static final String SUBJECT_NAME = "SUBJECT_NAME";
    private static final String SUBJECT_DESCRIPTION = "SUBJECT_DESC";
    private static final String TEST1 = "TEST_1";
    private static final String TEST2 = "TEST_2";
    private static final String TEST3 = "TEST_3";


    public DatabaseHandlerSubject(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        onUpgrade(db, 1, DATABASE_VERSION);
        String createTable = "CREATE TABLE " + SUBJECT_TABLE + "("
                + SUBJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SUBJECT_NAME + " TEXT,"
                + SUBJECT_DESCRIPTION + " TEXT,"
                + TEST1 + " DOUBLE," + TEST2 + " DOUBLE,"
                + TEST3 + " DOUBLE" + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SUBJECT_TABLE);
        onCreate(db);
    }


    public void addLearner(LearnerReport learner) {

        //adding a new learner
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SUBJECT_NAME, learner.getName());
        values.put(SUBJECT_DESCRIPTION, learner.getSurname());
        values.put(TEST1, learner.getTest1());
        values.put(TEST2, learner.getTest2());
        values.put(TEST3, learner.getTest3());


        db.insert(SUBJECT_TABLE, null, values);
        db.close();

    }


    public Subject getLearner(String name) throws SQLiteException {

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(SUBJECT_TABLE, new String[]{SUBJECT_ID, SUBJECT_NAME, SUBJECT_DESCRIPTION
                    , TEST1, TEST2, TEST3}, SUBJECT_NAME + " =?", new String[]
                    {String.valueOf(name)}, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }

            Subject objSubject = new Subject(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), Double.parseDouble(cursor.getString(3)), Double.parseDouble(cursor.getString(4)),
                    Double.parseDouble(cursor.getString(5)));
            return objSubject;
        } catch (SQLiteException ex) {
            throw new SQLiteException("Error while retrieving data\n" + ex.getMessage());
        }

    }
}
