package com.essenco.adviser.Data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.essenco.adviser.Domain.Advise;

import java.util.ArrayList;

public class SQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "advise_db";
    public SQLiteOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ColumnContract.CREATE_TABLE_ADVISE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " +  ColumnContract.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }
    public long createAdvise(Advise advise){
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ColumnContract.COLUMN_ID, advise.getAdviseId());
        values.put(ColumnContract.COLUMN_NAME_ADVISE, advise.getAdvise());
        // insert row
        long id = db.insert(ColumnContract.TABLE_NAME, null, values);
        // close db connection
        db.close();
        // return newly inserted row id
        return id;
    }
    public Advise selectAdviseById(long id){
        try{
            // get readable database as we are not inserting anything
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(ColumnContract.TABLE_NAME,
                    new String[]{ColumnContract.COLUMN_ID, ColumnContract.COLUMN_NAME_ADVISE},
                    ColumnContract.COLUMN_ID + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            // prepare advise object
            Advise advise = new Advise(
                    cursor.getInt(cursor.getColumnIndex(ColumnContract.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(ColumnContract.COLUMN_NAME_ADVISE)));
            // close the db connection
            cursor.close();
            return advise;
        }catch (Exception ex){
            // handle exception
        }
        return null;
    }
    public ArrayList<Advise> getAllAdvise() {
    //        List<Note> notes = new ArrayList<>();
    //
    //        // Select All Query
    //        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
    //                Note.COLUMN_TIMESTAMP + " DESC";
    //
    //        SQLiteDatabase db = this.getWritableDatabase();
    //        Cursor cursor = db.rawQuery(selectQuery, null);
    //
    //        // looping through all rows and adding to list
    //        if (cursor.moveToFirst()) {
    //            do {
    //                Note note = new Note();
    //                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
    //                note.setNote(cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)));
    //                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));
    //
    //                notes.add(note);
    //            } while (cursor.moveToNext());
    //        }
    //
    //        // close db connection
    //        db.close();
    //
    //        // return notes list
    //        return notes;
            return null;
    }
    public int getAdviseCount() {
    //        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
    //        SQLiteDatabase db = this.getReadableDatabase();
    //        Cursor cursor = db.rawQuery(countQuery, null);
    //
    //        int count = cursor.getCount();
    //        cursor.close();
    //
    //
    //        // return count
    //        return count;
        return 0;
    }
    public int updateAdvise(Advise advise) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ColumnContract.COLUMN_NAME_ADVISE, advise.getAdvise());

        // updating row
        return db.update(ColumnContract.TABLE_NAME, values, ColumnContract.COLUMN_ID + " = ?",
                new String[]{String.valueOf(advise.getAdviseId())});
    }
    public void deleteAdvise(Advise advise) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ColumnContract.TABLE_NAME, ColumnContract.COLUMN_ID + " = ?",
                new String[]{String.valueOf(advise.getAdviseId())});
        db.close();
    }
}
