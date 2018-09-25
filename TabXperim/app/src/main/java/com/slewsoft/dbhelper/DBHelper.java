package com.slewsoft.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "slewsoft.db";
    private static final int DB_VER = 1;

    private static final String TBL_NAME = "jobsite";
    private static final String COL_Name = "name";
    private static final String COL_Pix = "pix";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public void addBitmap(String name, byte[] image) throws SQLiteException {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_Name, name);
        cv.put(COL_Pix, image);
        db.insert(TBL_NAME, null, cv);

    }

    public byte[] getBitmapByName(String name) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] select = {COL_Name, COL_Pix};

        qb.setTables(TBL_NAME);
        Cursor c = qb.query(db, select, "Name = ?", new String[] {name}, null, null, null);
        byte[] result = null;
        if(c.moveToFirst()) {
            //TODO remove the loop and use the 1st resultset
            do{
                result = c.getBlob(c.getColumnIndex(COL_Name));
            } while (c.moveToNext());
        }
        return result;
    }
}
