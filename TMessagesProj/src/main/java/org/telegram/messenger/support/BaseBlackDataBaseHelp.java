package org.telegram.messenger.support;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseBlackDataBaseHelp  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public BaseBlackDataBaseHelp(Context context) {
        super(context, BaseDataBlackNames.FF.TABLE_NAME, null, DATABASE_VERSION);
    }


    private static final String SQL_CREATE_OUR_TABLE =
            "CREATE TABLE " + BaseDataBlackNames.FF.TABLE_NAME + " (" +
                    BaseDataBlackNames.FF._ID + " INTEGER PRIMARY KEY, " +
                    BaseDataBlackNames.FF.COLUMN_NAME + " TEXT, " +
                    BaseDataBlackNames.FF.COLUMN_VALUES + " LONG)";



    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_OUR_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

}