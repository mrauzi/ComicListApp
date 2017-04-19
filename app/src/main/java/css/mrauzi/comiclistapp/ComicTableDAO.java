package css.mrauzi.comiclistapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by mrauzi on 4/7/2017.
 */

public class ComicTableDAO {
    // database fields
    private SQLiteDatabase database;    // the database object
    private DatabaseHelper dbHelper;    // the database helper object

    /**
     * ComicTableDAO() - constructor to create a single instance of the database
     *
     * @param context
     */
    public ComicTableDAO(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    /**
     * open() - opens up the comic table on the database and gets a link to the SQLite database
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /**
     * close() - closes the SQLite database
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * createComic() - saves a new comic to the comic table in the SQLite database.
     *
     * @param comicName - name of the comic
     * @param comicPrice - price of the planet
     * @param comicVolume - the volume number of the comic
     * @return Comic - returns the comic that was created
     */
    public Comic createComic(String comicName, Double comicPrice, Integer comicVolume) {
        // store new values into a ContentValues object
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.DB_FIELD_COMICNAME, comicName);
        values.put(DatabaseHelper.DB_FIELD_COMICPRICE, comicPrice);
        values.put(DatabaseHelper.DB_FIELD_COMICVOLUME, comicVolume);
        //insert(String table, String nullColumnHack, ContentValues values)
        long insertId = database.insert(DatabaseHelper.DB_TABLE_NAME, null, values);
        Comic newComic = new Comic(insertId, comicName, comicPrice, comicVolume);
        return newComic;
    }

    /**
     * getAllComics() - get a list of all the comics in the database
     *
     * @return the list of comic objects
     */
    public List<Comic> getAllComics() {
        List<Comic> comics = new ArrayList<Comic>();
        // query the database for all the fields of all the records in the planet table
        // query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit)
        Cursor cursor = database.query(DatabaseHelper.DB_TABLE_NAME,
                null, null, null, null, null, null);
        // loop through the cursor converting each row into a planet object
        while (cursor.moveToNext()) {
            Comic comic = cursorToComic(cursor);
            comics.add(comic);
        }
        // make sure to close the cursor
        cursor.close();
        return comics;
    }

    /**
     * cursorToComic() - convert the current record the cursor points to into a comic object
     *
     * @param cursor - points to a record in the comic table of the SQLite database
     * @return  a comic object
     */
    private Comic cursorToComic(Cursor cursor) {
        Long id = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.DB_FIELD_ID));
        String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.DB_FIELD_COMICNAME));
        double price = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.DB_FIELD_COMICPRICE));
        int volume = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.DB_FIELD_COMICVOLUME));
        Comic comic = new Comic(id, name, price, volume);
        return comic;
    }

    /**
     * updateComic() - update the matching comic record in the database to match the comic object given
     * as a parameter
     *
     * @param comic - object with the new comic data to update the database
     * @return  number of rows updated - should generally be 1
     * */
    public int updateComic(Comic comic) {
        // store new values into a ContentValues object
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.DB_FIELD_COMICNAME, comic.getName());
        values.put(DatabaseHelper.DB_FIELD_COMICPRICE, comic.getPrice());
        values.put(DatabaseHelper.DB_FIELD_COMICVOLUME, comic.getVolume());

        // need an String array with the comic id in it
        String[] strId = new String[] { comic.get_id().toString() };
        // update(String table, ContentValues values, String whereClause, String[] whereArgs)
        return database.update(DatabaseHelper.DB_TABLE_NAME, values, DatabaseHelper.DB_FIELD_ID + " = ?", strId);
    }

    /**
     * deleteComic() - delete the given comic from the comic table in the database
     *
     * @param comic - object with the comic data to be deleted form the database
     * @return  number of rows deleted, should generally be 1
     * */
    public int deleteComic(Comic comic) {
        // need an String array with the comic id in it
        String[] strId = new String[] { comic.get_id().toString() };
        //delete(String table, String whereClause, String[] whereArgs)
        return database.delete(DatabaseHelper.DB_TABLE_NAME, DatabaseHelper.DB_FIELD_ID + " = ?", strId);
    }
}
