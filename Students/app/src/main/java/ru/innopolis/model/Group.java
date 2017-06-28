package ru.innopolis.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import ru.innopolis.utils.UniqueIdGenerator;

/**
 * Created by ibrahim on 6/8/2017.
 */
public class Group implements Parcelable {
    private Long groupId;
    private String groupName;
    private List<Student> students;
    private List<Lesson> lessons;

    public Group(String groupName) {
        this.groupName = groupName;
        this.groupId = UniqueIdGenerator.generateUid();
    }

    public Group(String groupName, List<Student> students, List<Lesson> lessons) {
        this.groupName = groupName;
        this.students = students;
        this.lessons = lessons;
        this.groupId = UniqueIdGenerator.generateUid();
    }

    public Long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (!groupId.equals(group.groupId)) return false;
        if (!groupName.equals(group.groupName)) return false;
        if (students != null ? !students.equals(group.students) : group.students != null)
            return false;
        return lessons != null ? lessons.equals(group.lessons) : group.lessons == null;

    }

    @Override
    public int hashCode() {
        int result = groupId.hashCode();
        result = 31 * result + groupName.hashCode();
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + (lessons != null ? lessons.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.groupId);
        dest.writeString(this.groupName);
        dest.writeList(this.students);
        dest.writeTypedList(this.lessons);
    }

    protected Group(Parcel in) {
        this.groupId = (Long) in.readValue(Long.class.getClassLoader());
        this.groupName = in.readString();
        this.students = new ArrayList<Student>();
        in.readList(this.students, Student.class.getClassLoader());
        this.lessons = in.createTypedArrayList(Lesson.CREATOR);
    }

    public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
