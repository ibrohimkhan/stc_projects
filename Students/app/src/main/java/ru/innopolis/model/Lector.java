package ru.innopolis.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import ru.innopolis.utils.UniqueIdGenerator;

/**
 * Created by ibrahim on 6/8/2017.
 */
public class Lector implements Parcelable {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Contact> contacts;

    private Account account;

    public Lector(Account account) {
        this.account = account;
        this.id = UniqueIdGenerator.generateUid();
    }

    public Lector(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.id = UniqueIdGenerator.generateUid();
    }

    public Lector(String firstName, String lastName, List<Contact> contacts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contacts = contacts;

        this.id = UniqueIdGenerator.generateUid();
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public Account getAccount() {
        return account;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return (21 + id.hashCode() * 42) + (21 + (firstName + " " + lastName).hashCode() * 42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Lector)) return false;
        if (this.id != ((Lector) obj).getId()) return false;

        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeTypedList(this.contacts);
        dest.writeParcelable(this.account, flags);
    }

    protected Lector(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.contacts = in.createTypedArrayList(Contact.CREATOR);
        this.account = in.readParcelable(Account.class.getClassLoader());
    }

    public static final Parcelable.Creator<Lector> CREATOR = new Parcelable.Creator<Lector>() {
        @Override
        public Lector createFromParcel(Parcel source) {
            return new Lector(source);
        }

        @Override
        public Lector[] newArray(int size) {
            return new Lector[size];
        }
    };
}
