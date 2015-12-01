package cs3340.project.runninggame.android.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import cs3340.project.runninggame.Data.DateTimePair;

/**
 * Used for setting up connection to or creating a database in which we store
 * all the run times from the game.
 * @author Jesus Ramos
 * @version 0.1
 * @since 11/30/2015
 */
public class DBAdapter {
    android.database.sqlite.SQLiteDatabase db;
    DatabaseHelper DBHelper;
    Context context;

    static final String KEY_ID = "_id";
    static final String KEY_DATE = "date";
    static final String KEY_TIME = "time";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "HiScoreDB";
    static final String DATABASE_TABLE = "HighScores";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "create table HighScores (_id text primary key," +
            "date text not null, " +
            "time real not null); ";

    /**
     * Creates a database linked to the AndroidLauncher activity via a context
     * @param ctx the context from which the function was called.
     */
    public DBAdapter(Context ctx)
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    /**
     * Either opens access to an existing database for reading/writing, or creates
     * a new database that will allow us to do both aforementioned functions.
     * @return a read/writable database
     * @throws SQLException if accessing or creating a database does not succeed
     */
    public DBAdapter open() throws SQLException
    {
        this.db = DBHelper.getWritableDatabase();

        return this;
    }

    /**
     * Inserts a time with it's tied unique ID and date into the database
     * @param id the unique ID generated for the time
     * @param date the date and time the run was completed
     * @param time the race's final time
     * @return the rowID of the newly added time in the database
     */
    public long insertTime(String id, String date, String time)
    {
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_ID, id);
        initialValues.put(KEY_DATE, date);
        initialValues.put(KEY_TIME, time);

        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Queries the database to retrieve all times. The function then
     * iterates through the entirety of the results, specifically
     * retrieving the time and it's paired date, and adding them to
     * a growing ArrayList.
     * @return the array list of DateTimePair's containing the paired time's and date's
     */
    public ArrayList<DateTimePair> loadTimes() {
        ArrayList<DateTimePair> times = new ArrayList<>();

        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cur = db.rawQuery(query, null);

        if (cur != null) cur.moveToFirst();
        do {
            times.add(new DateTimePair(cur.getString(1), cur.getDouble(2)));
        }
        while (cur.moveToNext());

        return times;
    }

    /**
     * Used for organizing the SQLiteOpenHelper functions into one class
     * that are used for creating or updating a database at the creation of a
     * DBAdapter instance.
     */
    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        /**
         * Calls the SQLiteOpenHelper constructor
         * @param context the context from which the creation took place
         */
        DatabaseHelper(Context context)
        {   super (context, DATABASE_NAME, null, DATABASE_VERSION); }

        /**
         * Creates a database when one does not exist
         * @param db the empty SQLiteDB Instance which does not contain a database.
         */
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try{
                db.execSQL(DATABASE_CREATE);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

        /**
         * Upgrades the database if a newer version of it exists
         * @param db the SQLite Database
         * @param oldVersion the old version number
         * @param newVersion the new version number
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w(TAG, "upgrading from version" + oldVersion + " to " + newVersion);
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
}
