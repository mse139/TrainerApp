package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import database.CustomerSchema.CustomerTable.*;
/**
 * Created by mike on 9/10/17.
 */

public class CustomerBaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "fitness";

    // ctor to create the database
    public CustomerBaseHelper(Context context) {
        super(context,DB_NAME,null,VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the customer table
        db.execSQL("create table " + CustomerSchema.CustomerTable.NAME +
        "(_id integer primary key autoincrement, " +
        Cols.CUST_NAME + "," +
                Cols.ADDRESS + "," +
                Cols.EMAIL + "," +
                Cols.PHONE_NUMBER + "," +
                Cols.UUID + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
