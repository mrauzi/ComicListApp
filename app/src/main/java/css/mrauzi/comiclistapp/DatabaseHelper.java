package css.mrauzi.comiclistapp;


import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by mrauzi on 4/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="comic_constants.db"; // the name of the database
    private static final int SCHEMA=1;                              // the version of the database
    static final String DB_TABLE_NAME="comics";                     // the name of the database table
    static final String DB_FIELD_ID ="_id";                         // the ID of the comic
    static final String DB_FIELD_COMICNAME ="title";                // the name of the comic
    static final String DB_FIELD_COMICPRICE ="price";               // the price of the comic
    static final String DB_FIELD_COMICVOLUME = "volume";            // the volume number of the comic
    static final String DB_FIELD_COMICSTATUS = "status";            // the status of checkbox in the comic list

    private static DatabaseHelper singleInstance;                   // an instance of the database helper class

    // Database creation SQL statement
    private static final String DATABASE_CREATE_SQL_STRING = "create table "+ DB_TABLE_NAME
            + " ( "+DB_FIELD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +DB_FIELD_COMICNAME +" TEXT, "
            +DB_FIELD_COMICPRICE +" REAL, "
            +DB_FIELD_COMICVOLUME +" INTEGER, "
            +DB_FIELD_COMICSTATUS +" TEXT);";

    // do not call this anymore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    /**
     * getInstance() - will make sure that only one instance of the database is used at a time.
     *
     * @param context
     */
    public static synchronized DatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (singleInstance == null) {
            singleInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return singleInstance;
    }

    /**
     * onCreate() - execute command to create the database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_SQL_STRING);
    }

    /**
     * onUpgrade() - upgrades the database from the old version to a new version.  Will drop the database
     * table if it exists and create a new version.
     *
     * @param db - the database to be upgraded
     * @param oldVersion - the previous version of the database
     * @param newVersion - the newVersion of the database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NAME);
        onCreate(db);
    }
}
