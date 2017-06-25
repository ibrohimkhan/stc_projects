package ru.innopolis.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ibrahim on 6/8/2017.
 */
public class Contact implements Parcelable {
    private String value;
    private ContactType contactType;
    private int icon;

    public Contact(String value, ContactType contactType) {
        this.value = value;
        this.contactType = contactType;
    }

    public Contact(String value, ContactType contactType, int icon) {
        this.value = value;
        this.contactType = contactType;
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public int hashCode() {
        return (21 + value.hashCode() * 42) + (21 + contactType.hashCode() * 42);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Contact)) return false;
        if (((Contact) obj).getValue() != this.value) return false;

        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.value);
        dest.writeInt(this.contactType == null ? -1 : this.contactType.ordinal());
        dest.writeInt(this.icon);
    }

    protected Contact(Parcel in) {
        this.value = in.readString();
        int tmpContactType = in.readInt();
        this.contactType = tmpContactType == -1 ? null : ContactType.values()[tmpContactType];
        this.icon = in.readInt();
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
