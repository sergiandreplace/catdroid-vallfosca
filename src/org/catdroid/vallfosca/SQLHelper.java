package org.catdroid.vallfosca;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper 
{
	// Table column names
	public static final String KEY_ID = "id";
    public static final String KEY_LAT = "lat";
    public static final String KEY_LNG = "lng";
    public static final String KEY_ELEVATION = "elevation";    
    public static final String KEY_TITLE = "title";    
    public static final String KEY_DISTANCE = "distance";    
    public static final String KEY_HAS_DETAIL_PAGE = "has_detail_page";    
    public static final String KEY_WEBPAGE = "webpage";
    
    // Tag used in LogCat output
    private static final String TAG = "SQLHelper";
 
    // Database values
    private static final String DATABASE_NAME = "vallfosca";
    private static final String DATABASE_TABLE = "pois";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE =
        "CREATE TABLE pois (id INTEGER PRIMARY KEY AUTOINCREMENT, "
        + "lat REAL, " // latitude: required by mixare
        + "lng REAL, " // longitude: required by mixare
        + "elevation REAL, " // elevation: required by mixare
        + "title TEXT, "  // title text for marker: required by mixare
        + "distance REAL, " // distance between POI and current pos: optional for mixare
        + "has_detail_page INTEGER, " // optional for mixare. MUST be 1 if webpage is given
        + "webpage TEXT);"; // optional for mixare. URL of marker text. If given, has_detail_page MUST be 1
 
    private final Context context; 
 
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
 
    public SQLHelper(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
 
    private class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
 
        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
            // POI insertion into Database
            insertPOI(42.47361, 0.9919, 0.0, "Capdella", 0.0, 1, "webpage");
            insertPOI(42.46682, 0.9908, 0.0, "La Central de Capdella", 0.0, 1, "webpage");
            insertPOI(42.45551, 0.9879, 0.0, "Espui", 0.0, 1, "webpage");
            insertPOI(42.44270, 0.9920, 0.0, "Aiguabella", 0.0, 1, "webpage");
            insertPOI(42.42165, 0.9821, 0.0, "La Torre de Capdella", 0.0, 1, "webpage");
            insertPOI(42.40479, 0.9742, 0.0, "Molinos", 0.0, 1, "webpage");
            insertPOI(42.40361, 0.9650, 0.0, "Astell", 0.0, 1, "webpage");
            insertPOI(42.40212, 0.9868, 0.0, "Mont-rós", 0.0, 1, "webpage");
            insertPOI(42.39811, 0.9888, 0.0, "Pobellà", 0.0, 1, "webpage");
            insertPOI(42.39671, 0.9823, 0.0, "Paüls", 0.0, 1, "webpage");
            insertPOI(42.39843, 0.9430, 0.0, "Aguiró", 0.0, 1, "webpage");
            insertPOI(42.39468, 0.9497, 0.0, "Oveix", 0.0, 1, "webpage");
            insertPOI(42.38698, 0.9542, 0.0, "Castell-estaó", 0.0, 1, "webpage");
            insertPOI(42.38174, 0.9649, 0.0, "La Plana de Mont-rós", 0.0, 1, "webpage");
            insertPOI(42.37429, 0.9676, 0.0, "Beranui", 0.0, 1, "webpage");
            insertPOI(42.37110, 0.9486, 0.0, "Antist", 0.0, 1, "webpage");
            insertPOI(42.35866, 0.9385, 0.0, "Estavill", 0.0, 1, "webpage");
            insertPOI(42.35032, 0.9861, 0.0, "Envall", 0.0, 1, "webpage");
            insertPOI(42.34401, 0.9601, 0.0, "La Pobleta de Bellveí", 0.0, 1, "webpage");
            insertPOI(42.32526, 0.9385, 0.0, "Senterada", 0.0, 1, "webpage");

        }
 
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }    
 
    /**
     * Opens writable database
     * @return Reference to successfully opened database
     * @throws SQLException
     */
    public SQLHelper open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }
 
    /**
     * Closes database    
     */
    public void close() 
    {
        DBHelper.close();
    }
 
    /**
     * Inserts a new POI into the table
     * @param lat Latitude of POI
     * @param lng Longitude of POI
     * @param elevation Elevation of POI
     * @param title Text that will be shown in marker
     * @param distance Distance (optional)
     * @param has_detail_page Must be 1 if POI has associated webpage
     * @param webpage URL of webpage associated to POI
     * @return ID of successfully inserted POI
     */
	public long insertPOI(double lat, double lng, double elevation, String title, double distance, int has_detail_page, String webpage ) {
		//add new POI to the table
		ContentValues rowValues = new ContentValues();
		rowValues.put(SQLHelper.KEY_LAT, lat);
		rowValues.put(SQLHelper.KEY_LNG, lng);
		rowValues.put(SQLHelper.KEY_ELEVATION, elevation);
		rowValues.put(SQLHelper.KEY_TITLE, title);
		rowValues.put(SQLHelper.KEY_DISTANCE, distance);
		rowValues.put(SQLHelper.KEY_HAS_DETAIL_PAGE, has_detail_page);
		rowValues.put(SQLHelper.KEY_WEBPAGE, webpage);
        return db.insert(DATABASE_TABLE, null, rowValues);
    }
 
    /**
     * Deletes a particular POI
     * @param rowId ID of POI to be deleted from the table
     * @return 1 on success
     */
    public boolean deletePOI(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ID + 
        		"=" + rowId, null) > 0;
    }
 
    /**
     * Returns all POIs in the table
     * @return A cursor containing all POIs in the table
     */
    public Cursor getAllPOIs() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ID, 
        		KEY_LAT,
        		KEY_LNG,
        		KEY_ELEVATION,
        		KEY_TITLE,
                KEY_DISTANCE,
                KEY_HAS_DETAIL_PAGE,
                KEY_WEBPAGE}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
 
    /**
     * Retrieve all data for a single POI
     * @param rowId ID of POI
     * @return A cursor containing all fields for this POI
     * @throws SQLException
     */
    public Cursor getPOI(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ID,
                		KEY_LAT,
                		KEY_LNG,
                		KEY_ELEVATION,
                		KEY_TITLE,
                		KEY_DISTANCE,
                		KEY_HAS_DETAIL_PAGE,
                		KEY_WEBPAGE}, 
                		KEY_ID + "=" + rowId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
 
    /**
     * Updates all values for a single POI
     * @param rowId ID of POI
     * @param lat Latitude of POI
     * @param lng Longitude of POI
     * @param elevation Elevation of POI
     * @param title Text that will be shown in marker
     * @param distance Distance (optional)
     * @param has_detail_page Must be 1 if POI has associated webpage
     * @param webpage URL of webpage associated to POI
     * @return ID of successfully updated POI
     */
	public boolean updatePOI(long rowId, double lat, double lng, double elevation, String title, double distance, int has_detail_page, String webpage ) {
		ContentValues rowValues = new ContentValues();
		rowValues.put(SQLHelper.KEY_LAT, lat);
		rowValues.put(SQLHelper.KEY_LNG, lng);
		rowValues.put(SQLHelper.KEY_ELEVATION, elevation);
		rowValues.put(SQLHelper.KEY_TITLE, title);
		rowValues.put(SQLHelper.KEY_DISTANCE, distance);
		rowValues.put(SQLHelper.KEY_HAS_DETAIL_PAGE, has_detail_page);
		rowValues.put(SQLHelper.KEY_WEBPAGE, webpage);
        return db.update(DATABASE_TABLE, rowValues, 
                         KEY_ID + "=" + rowId, null) > 0;
    }
}