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
    private Long lessonId;
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

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long groupId) {
        this.lessonId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        if (!id.equals(journal.id)) return false;
        if (!date.equals(journal.date)) return false;
        if (lessonId != null ? !lessonId.equals(journal.lessonId) : journal.lessonId != null)
            return false;
        return listeners != null ? listeners.equals(journal.listeners) : journal.listeners == null;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (lessonId != null ? lessonId.hashCode() : 0);
        result = 31 * result + (listeners != null ? listeners.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeValue(this.lessonId);
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
        this.lessonId = (Long) in.readValue(Long.class.getClassLoader());
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
