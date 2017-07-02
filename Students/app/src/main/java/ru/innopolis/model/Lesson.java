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
    private Long groupId;

    public Lesson(String subject) {
        this.subject = subject;
        this.lessonId = UniqueIdGenerator.generateUid();
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (!lessonId.equals(lesson.lessonId)) return false;
        if (date != null ? !date.equals(lesson.date) : lesson.date != null) return false;
        if (!subject.equals(lesson.subject)) return false;
        if (description != null ? !description.equals(lesson.description) : lesson.description != null)
            return false;
        if (lector != null ? !lector.equals(lesson.lector) : lesson.lector != null) return false;
        if (students != null ? !students.equals(lesson.students) : lesson.students != null)
            return false;
        return journal != null ? journal.equals(lesson.journal) : lesson.journal == null;

    }

    @Override
    public int hashCode() {
        int result = lessonId.hashCode();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + subject.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (lector != null ? lector.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + (journal != null ? journal.hashCode() : 0);
        return result;
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
        dest.writeTypedList(this.students);
        dest.writeParcelable(this.journal, flags);
        dest.writeValue(this.groupId);
    }

    protected Lesson(Parcel in) {
        this.lessonId = (Long) in.readValue(Long.class.getClassLoader());
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.subject = in.readString();
        this.description = in.readString();
        this.lector = in.readParcelable(Lector.class.getClassLoader());
        this.students = in.createTypedArrayList(Student.CREATOR);
        this.journal = in.readParcelable(Journal.class.getClassLoader());
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Lesson> CREATOR = new Creator<Lesson>() {
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
