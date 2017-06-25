package ru.innopolis.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.innopolis.utils.UniqueIdGenerator;

/**
 * Created by ibrahim on 6/8/2017. *
 */
public class Student implements Parcelable {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long id;
    private Group group;
    private List<Contact> contacts;

    private Account account;

    public Student(Account account) {
        this.account = account;
        this.id = UniqueIdGenerator.generateUid();
    }

    public Student(String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contacts = new ArrayList<>();

        this.id = UniqueIdGenerator.generateUid();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public Group getGroup() {
        return group;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!firstName.equals(student.firstName)) return false;
        if (!lastName.equals(student.lastName)) return false;
        if (!dateOfBirth.equals(student.dateOfBirth)) return false;
        return id.equals(student.id);

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeLong(this.dateOfBirth != null ? this.dateOfBirth.getTime() : -1);
        dest.writeValue(this.id);
        dest.writeParcelable(this.group, flags);
        dest.writeTypedList(this.contacts);
        dest.writeParcelable(this.account, flags);
    }

    protected Student(Parcel in) {
        this.firstName = in.readString();
        this.lastName = in.readString();
        long tmpDateOfBirth = in.readLong();
        this.dateOfBirth = tmpDateOfBirth == -1 ? null : new Date(tmpDateOfBirth);
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.group = in.readParcelable(Group.class.getClassLoader());
        this.contacts = in.createTypedArrayList(Contact.CREATOR);
        this.account = in.readParcelable(Account.class.getClassLoader());
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
