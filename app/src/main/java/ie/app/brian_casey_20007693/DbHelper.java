package ie.app.brian_casey_20007693;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Brian Casey 2017
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String TAG = DbHelper.class.getSimpleName();
    public static final String DB_NAME = "myapp.db";
    public static final int DB_VERSION = 1;
    public static final String USER_TABLE = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";

    // Create Database tables users
    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASS + " TEXT);";

    //Databse constructor
    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    //Fuction to store user details
    public void addUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }

    public boolean getUser(String email, String pass){
        //Check if user and email match the database
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COLUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+pass+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor used to read from database
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        //if record for user found return true
        if (cursor.getCount() > 0) {

            return true;
        }
        cursor.close();
        db.close();

        return false;
    }

    public String getUsername() throws SQLException {
        String username = "";
        //Cursor used to read from database
        Cursor cursor = this.getReadableDatabase().query(
                USER_TABLE, new String[] {COLUMN_EMAIL},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return username;
    }
}
