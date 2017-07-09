package ru.innopolis.justchat.manager;

import android.content.Context;

import java.util.List;

import ru.innopolis.justchat.db.ILanguages;
import ru.innopolis.justchat.db.LanguageDB;
import ru.innopolis.justchat.model.Language;

/**
 * Created by ibrahim on 7/8/2017.
 */

public class SharedDataManager implements ILanguages {
    private LanguageDB language;
    private Context context;

    public SharedDataManager(Context context) {
        this.context = context;
        language = new LanguageDB(this.context);
    }

    @Override
    public List<Language> getAllLanguages() {
        return language.getAllLanguages();
    }
}
