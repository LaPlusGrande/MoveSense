package de.fhkiel.srcms.apps.therapy.physical._database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    private static final String DB_NAME = "userdb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "myuser";

    private static final String ID_COL = "id";

    private static final String NAME_COL = "name";

    private static final String ITERATION_COL = "iteration";

    private static final String ANIMATIONONE_COL = "description";

    private static final String ANIMATIONTWO_COL = "tracks";


    public DBHandler(Context context) { super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ITERATION_COL + " TEXT,"
                + ANIMATIONONE_COL + " TEXT,"
                + ANIMATIONTWO_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new user to our sqlite database.
    public void addNewUser(String userName, String iteration, String animationOne, String animationTwo) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, userName);
        values.put(ITERATION_COL, iteration);
        values.put(ANIMATIONONE_COL, animationOne);
        values.put(ANIMATIONTWO_COL, animationTwo);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    public ArrayList<UserModal> readUser() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a user with query to read data from database.
        Cursor cursorUser = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<UserModal> userModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorUser.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                userModalArrayList.add(new UserModal(cursorUser.getString(1),
                        cursorUser.getString(4),
                        cursorUser.getString(2),
                        cursorUser.getString(3)));
            } while (cursorUser.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorUser.close();
        return userModalArrayList;
    }

    // below is the method for updating user
    public void updateUser(String originalUserName, String userName, String iteration,
                           String animationOne, String animationTwo) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, userName);
        values.put(ITERATION_COL, animationTwo);
        values.put(ANIMATIONONE_COL, iteration);
        values.put(ANIMATIONTWO_COL, animationOne);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of user which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalUserName});
        db.close();
    }
    // below is the method for deleting User.
    public void deleteUser(String userName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", new String[]{userName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
