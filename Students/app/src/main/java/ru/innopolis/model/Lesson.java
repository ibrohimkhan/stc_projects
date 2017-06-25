package ru.innopolis.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import ru.innopolis.utils.UniqueIdGenerator;

/**
 * Created by ibrahim on 6/8/2017.
 */
public class Lesson implements Parcelable {
    private Long lessonId;
    private Date date;
    private String subject;
    private String description;
    private Lector lector;
    private List<Student> students;
    private Journal journal;

    public Lesson(String subject) {
        this.subject = subject;
    }

    public Lesson(Date date, String subject, String description) {
        this.date = date;
        this.subject = subject;
        this.description = description;
        this.lessonId = UniqueIdGenerator.generateUid();
    }

    public Long getLessonId() {
        return lessonId;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public Journal getJournal() {
        return journal;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    @Override
    public int hashCode() {
        return (int) ((21 + lessonId * 42) + (21 + subject.hashCode() * 42));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Lesson)) return false;
        if (this.lessonId != ((Lesson) obj).getLessonId()) return false;

        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.lessonId);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeString(this.subject);
        dest.writeString(this.description);
        dest.writeParcelable(this.lector, flags);
        dest.writeList(this.students);
        dest.writeParcelable(this.journal, flags);
    }

    protected Lesson(Parcel in) {
        this.lessonId = (Long) in.readValue(Long.class.getClassLoader());
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.subject = in.readString();
        this.description = in.readString();
        this.lector = in.readParcelable(Lector.class.getClassLoader());
        this.students = new ArrayList<Student>();
        in.readList(this.students, Student.class.getClassLoader());
        this.journal = in.readParcelable(Journal.class.getClassLoader());
    }

    public static final Parcelable.Creator<Lesson> CREATOR = new Parcelable.Creator<Lesson>() {
        @Override
        public Lesson createFromParcel(Parcel source) {
            return new Lesson(source);
        }

        @Override
        public Lesson[] newArray(int size) {
            return new Lesson[size];
        }
    };
}
