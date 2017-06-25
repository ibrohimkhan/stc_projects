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
    public int hashCode() {
        return (21 + groupId.hashCode() * 42) + (21 + groupName.hashCode() * 42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Group)) return false;
        if (this.groupId != ((Group) obj).getGroupId()) return false;

        return true;
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
