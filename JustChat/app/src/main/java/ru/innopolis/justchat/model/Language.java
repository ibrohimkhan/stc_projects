package ru.innopolis.justchat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by ibrahim on 7/8/2017.
 */

public class Language implements Parcelable {
    private long id;
    private String language;
    private int flag;

    public Language(String language, int flag) {
        this.language = language;
        this.flag = flag;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language1 = (Language) o;

        if (id != language1.id) return false;
        if (flag != language1.flag) return false;
        return language.equals(language1.language);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + language.hashCode();
        result = 31 * result + flag;
        return result;
    }

    public static Comparator<Language> LanguageNameComparator = new Comparator<Language>() {
        @Override
        public int compare(Language l1, Language l2) {
            String languageName1 = l1.getLanguage().toLowerCase();
            String languageName2 = l2.getLanguage().toLowerCase();

            return languageName1.compareTo(languageName2);
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.language);
        dest.writeInt(this.flag);
    }

    protected Language(Parcel in) {
        this.id = in.readLong();
        this.language = in.readString();
        this.flag = in.readInt();
    }

    public static final Parcelable.Creator<Language> CREATOR = new Parcelable.Creator<Language>() {
        @Override
        public Language createFromParcel(Parcel source) {
            return new Language(source);
        }

        @Override
        public Language[] newArray(int size) {
            return new Language[size];
        }
    };
}
