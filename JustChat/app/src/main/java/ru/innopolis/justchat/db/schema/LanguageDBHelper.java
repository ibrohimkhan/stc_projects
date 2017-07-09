package ru.innopolis.justchat.db.schema;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Iterator;

/**
 * Created by ibrahim on 7/8/2017.
 */
public class LanguageDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "Language.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_LANGUAGES =
            "CREATE TABLE " + LanguageContract.LanguageEntry.TABLE_NAME + " (" +
                    LanguageContract.LanguageEntry._ID + " INTEGER PRIMARY KEY, " +
                    LanguageContract.LanguageEntry.COLUMN_NAME_LANGUAGE + TEXT_TYPE + COMMA_SEP +
                    LanguageContract.LanguageEntry.COLUMN_NAME_FLAG + INTEGER_TYPE + " )";

    private static final String SQL_DELETE_LANGUAGES =
            "DROP TABLE IF EXISTS " + LanguageContract.LanguageEntry.TABLE_NAME;

    public LanguageDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_LANGUAGES);
        insertAllLanguages(db);
    }

    private void insertAllLanguages(SQLiteDatabase db) {
        Iterator<String> iterator = LanguageContract.ALL_LANGUAGES.keySet().iterator();

        while (iterator.hasNext()) {
            String item = iterator.next();

            ContentValues cv = new ContentValues();
            cv.put(LanguageContract.LanguageEntry.COLUMN_NAME_LANGUAGE, item);
            cv.put(LanguageContract.LanguageEntry.COLUMN_NAME_FLAG, LanguageContract.ALL_LANGUAGES.get(item));

            db.insert(LanguageContract.LanguageEntry.TABLE_NAME, null, cv);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_LANGUAGES);
        onCreate(db);
    }
}
