package ru.innopolis.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ru.innopolis.utils.UniqueIdGenerator;

/**
 * Created by ibrahim on 6/8/2017.
 */
public class Journal implements Parcelable {
    private Long id;
    private Date date;
    private Map<Student, Boolean> listeners;

    public Journal(Date date) {
        this.date = date;
        this.id = UniqueIdGenerator.generateUid();
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<Student, Boolean> getListeners() {
        return listeners;
    }

    public void setListeners(Map<Student, Boolean> listeners) {
        this.listeners = listeners;
    }

    @Override
    public int hashCode() {
        return (int) (21 + id.hashCode() * 42) + (21 + date.hashCode() * 42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Journal)) return false;
        if (this.id != ((Journal) obj).getId()) return false;

        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeInt(this.listeners.size());
        for (Map.Entry<Student, Boolean> entry : this.listeners.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags);
            dest.writeValue(entry.getValue());
        }
    }

    protected Journal(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        int listenersSize = in.readInt();
        this.listeners = new HashMap<Student, Boolean>(listenersSize);
        for (int i = 0; i < listenersSize; i++) {
            Student key = in.readParcelable(Student.class.getClassLoader());
            Boolean value = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.listeners.put(key, value);
        }
    }

    public static final Creator<Journal> CREATOR = new Creator<Journal>() {
        @Override
        public Journal createFromParcel(Parcel source) {
            return new Journal(source);
        }

        @Override
        public Journal[] newArray(int size) {
            return new Journal[size];
        }
    };
}
