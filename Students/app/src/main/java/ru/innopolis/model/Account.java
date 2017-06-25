package ru.innopolis.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ibrahim on 6/22/2017.
 */

public class Account implements Parcelable {
    private Long id;
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return (int) (21 + id * 42) + (21 + username.hashCode() * 42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Account)) return false;
        if (!this.username.equals(((Account) obj).getUsername())) return false;
        if (this.id != ((Account) obj).getId()) return false;

        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    protected Account(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
