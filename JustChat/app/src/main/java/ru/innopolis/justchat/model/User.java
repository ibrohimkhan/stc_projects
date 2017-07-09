package ru.innopolis.justchat.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ibrahim on 7/5/2017.
 */

public class User implements Parcelable {
    private Long id;
    private String firstName;
    private String lastName;

    private Account account;
    private UserType type;
    private State state;
    private Language language;

    public User(Account account) {
        this.account = account;
    }

    public User(String firstName, String lastName, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null)
            return false;
        if (!account.equals(user.account)) return false;
        if (type != user.type) return false;
        return state == user.state;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + account.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
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
        dest.writeParcelable(this.account, flags);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeInt(this.state == null ? -1 : this.state.ordinal());
        dest.writeParcelable(this.language, flags);
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.account = in.readParcelable(Account.class.getClassLoader());
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : UserType.values()[tmpType];
        int tmpState = in.readInt();
        this.state = tmpState == -1 ? null : State.values()[tmpState];
        this.language = in.readParcelable(Language.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
