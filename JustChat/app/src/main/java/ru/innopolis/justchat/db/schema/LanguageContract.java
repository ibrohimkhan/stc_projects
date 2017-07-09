package ru.innopolis.justchat.db.schema;

import android.provider.BaseColumns;

import java.util.HashMap;
import java.util.Map;

import ru.innopolis.justchat.R;

/**
 * Created by ibrahim on 7/8/2017.
 */
public final class LanguageContract {
    public static final Map<String, Integer> ALL_LANGUAGES = new HashMap<>();

    static {
        ALL_LANGUAGES.put("Afrikanns", R.drawable.afrikanns);
        ALL_LANGUAGES.put("Albanian", R.drawable.albanian);
        ALL_LANGUAGES.put("Arabic", R.drawable.arabic);
        ALL_LANGUAGES.put("Armenian", R.drawable.armenian);
        ALL_LANGUAGES.put("Basque", R.drawable.basque);
        ALL_LANGUAGES.put("Bengali", R.drawable.bengali);
        ALL_LANGUAGES.put("Bulgarian", R.drawable.bulgarian);
        ALL_LANGUAGES.put("Catalan", R.drawable.catalan);
        ALL_LANGUAGES.put("Cambodian", R.drawable.cambodian);
        ALL_LANGUAGES.put("Chinese (Mandarin)", R.drawable.chinese);
        ALL_LANGUAGES.put("Croation", R.drawable.croation);
        ALL_LANGUAGES.put("Czech", R.drawable.czech);
        ALL_LANGUAGES.put("Danish", R.drawable.danish);
        ALL_LANGUAGES.put("Dutch", R.drawable.dutch);
        ALL_LANGUAGES.put("English", R.drawable.english);
        ALL_LANGUAGES.put("Estonian", R.drawable.estonian);
        ALL_LANGUAGES.put("Fiji", R.drawable.fiji);
        ALL_LANGUAGES.put("Finnish", R.drawable.finnish);
        ALL_LANGUAGES.put("French", R.drawable.french);
        ALL_LANGUAGES.put("Georgian", R.drawable.georgian);
        ALL_LANGUAGES.put("German", R.drawable.german);
        ALL_LANGUAGES.put("Greek", R.drawable.greek);
        ALL_LANGUAGES.put("Gujarati", R.drawable.gujarati);
        ALL_LANGUAGES.put("Hebrew", R.drawable.hebrew);
        ALL_LANGUAGES.put("Hindi", R.drawable.hindi);
        ALL_LANGUAGES.put("Hungarian", R.drawable.hungarian);
        ALL_LANGUAGES.put("Icelandic", R.drawable.icelandic);
        ALL_LANGUAGES.put("Indonesian", R.drawable.indonesian);
        ALL_LANGUAGES.put("Irish", R.drawable.irish);
        ALL_LANGUAGES.put("Italian", R.drawable.italian);
        ALL_LANGUAGES.put("Japanese", R.drawable.japanese);
        ALL_LANGUAGES.put("Javanese", R.drawable.javanese);
        ALL_LANGUAGES.put("Kazakh", R.drawable.kazakh);
        ALL_LANGUAGES.put("Korean", R.drawable.korean);
        ALL_LANGUAGES.put("Kyrgyz", R.drawable.kyrgyz);
        ALL_LANGUAGES.put("Latin", R.drawable.latin);
        ALL_LANGUAGES.put("Latvian", R.drawable.latvian);
        ALL_LANGUAGES.put("Lithuanian", R.drawable.lithuanian);
        ALL_LANGUAGES.put("Macedonian", R.drawable.macedonian);
        ALL_LANGUAGES.put("Malay", R.drawable.malay);
        ALL_LANGUAGES.put("Malayalam", R.drawable.malayalam);
        ALL_LANGUAGES.put("Maltese", R.drawable.maltese);
        ALL_LANGUAGES.put("Maori", R.drawable.maori);
        ALL_LANGUAGES.put("Marathi", R.drawable.marathi);
        ALL_LANGUAGES.put("Mongolian", R.drawable.mongolian);
        ALL_LANGUAGES.put("Nepali", R.drawable.nepali);
        ALL_LANGUAGES.put("Norwegian", R.drawable.norwegian);
        ALL_LANGUAGES.put("Persian", R.drawable.persian);
        ALL_LANGUAGES.put("Polish", R.drawable.polish);
        ALL_LANGUAGES.put("Portuguese", R.drawable.portuguese);
        ALL_LANGUAGES.put("Punjabi", R.drawable.punjabi);
        ALL_LANGUAGES.put("Quechua", R.drawable.quechua);
        ALL_LANGUAGES.put("Romanian", R.drawable.romanian);
        ALL_LANGUAGES.put("Russian", R.drawable.russian);
        ALL_LANGUAGES.put("Samoan", R.drawable.samoan);
        ALL_LANGUAGES.put("Serbian", R.drawable.serbian);
        ALL_LANGUAGES.put("Slovak", R.drawable.slovak);
        ALL_LANGUAGES.put("Slovenian", R.drawable.slovenian);
        ALL_LANGUAGES.put("Spanish", R.drawable.spanish);
        ALL_LANGUAGES.put("Swahili", R.drawable.swahili);
        ALL_LANGUAGES.put("Swedish", R.drawable.swedish);
        ALL_LANGUAGES.put("Tamil", R.drawable.tamil);
        ALL_LANGUAGES.put("Tatar", R.drawable.tatar);
        ALL_LANGUAGES.put("Telugu", R.drawable.telugu);
        ALL_LANGUAGES.put("Thai", R.drawable.thai);
        ALL_LANGUAGES.put("Tibetan", R.drawable.tibetan);
        ALL_LANGUAGES.put("Tonga", R.drawable.tonga);
        ALL_LANGUAGES.put("Turkish", R.drawable.turkish);
        ALL_LANGUAGES.put("Turkmen", R.drawable.turkmen);
        ALL_LANGUAGES.put("Ukranian", R.drawable.ukranian);
        ALL_LANGUAGES.put("Urdu", R.drawable.urdu);
        ALL_LANGUAGES.put("Uzbek", R.drawable.uzbek);
        ALL_LANGUAGES.put("Vietnamese", R.drawable.vietnamese);
        ALL_LANGUAGES.put("Welsh", R.drawable.welsh);
        ALL_LANGUAGES.put("Xhosa", R.drawable.xhosa);
    }

    public LanguageContract() {}

    public static abstract class LanguageEntry implements BaseColumns {
        public static final String TABLE_NAME = "language";
        public static final String COLUMN_NAME_LANGUAGE = "language";
        public static final String COLUMN_NAME_FLAG = "flag";
    }
}
