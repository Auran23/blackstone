package com.example.adilbekmailanov.speechanalysis;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static String DB_PATH;
    public static final String DB_NAME = "bad_words.sql";
    public static final String TABLE_NAME = "words";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PARASITE_WORD = "parasite_words";
    public static final String COLUMN_ALTERNATIVES = "alternatives";
    public static final String COLUMN_CAN_DELETED = "can_deleted";
    public static final String COLUMN_CATEGORIES_ID = "categories_id";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
    }

    public ArrayList<WordModel> loadAllWords() {
        openDataBase();
        ArrayList<WordModel> arrayList = new ArrayList<>();
        Cursor cursor = myDataBase.query(TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int parasite_word_index = cursor.getColumnIndex(COLUMN_PARASITE_WORD);
            int alternative_word_index = cursor.getColumnIndex(COLUMN_ALTERNATIVES);
            String parasite_word = cursor.getString(parasite_word_index);
            String alternative_word = cursor.getString(alternative_word_index);
            arrayList.add(new WordModel(parasite_word, alternative_word));
        }
        cursor.close();
        myDataBase.close();
        return arrayList;
    }

    WordModel getWordModel(String word) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_PARASITE_WORD,
                        COLUMN_ALTERNATIVES }, COLUMN_PARASITE_WORD + "=?",
                new String[] { word }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        WordModel wordModel = new WordModel(cursor.getString(0), cursor.getString(1));

        return wordModel;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if(dbExist){
        }else{
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        String outFileName = DB_PATH + DB_NAME;

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null)
            myDataBase.close();

        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}