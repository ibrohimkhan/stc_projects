package ru.innopolis.justchat.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.justchat.db.schema.LanguageContract;
import ru.innopolis.justchat.db.schema.LanguageDBHelper;
import ru.innopolis.justchat.model.Language;

/**
 * Created by ibrahim on 7/8/2017.
 */

public class LanguageDB implements ILanguages {

    private Context context;
    private LanguageDBHelper languageDb;
    private SQLiteDatabase db;

    public LanguageDB(Context context) {
        this.context = context;
        languageDb = new LanguageDBHelper(this.context);
        db = languageDb.getReadableDatabase();
    }

    @Override
    public List<Language> getAllLanguages() {
        List<Language> langs = new ArrayList<>();

        Cursor cursor = db.query(LanguageContract.LanguageEntry.TABLE_NAME,
                new String[] {LanguageContract.LanguageEntry.COLUMN_NAME_LANGUAGE, LanguageContract.LanguageEntry.COLUMN_NAME_FLAG},
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            Language language = new Language(cursor.getString(0), cursor.getInt(1));
            langs.add(language);
        }

        cursor.close();
        db.close();

        return langs;
    }
}
