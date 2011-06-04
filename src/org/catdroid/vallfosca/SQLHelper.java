package org.catdroid.vallfosca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "VallFosca";
    private static final String TABLE_NAME = "POIs";
    private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY, " +
                "lat REAL, " +
                "lng REAL, " +
                "elevation REAL, " +
                "title TEXT, " +
                "distance REAL, " +
                "has_detail_page INTEGER, " +
                "webpage TEXT);";

    SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
}