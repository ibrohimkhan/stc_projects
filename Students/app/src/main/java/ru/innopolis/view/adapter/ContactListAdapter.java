package ru.innopolis.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.innopolis.model.Contact;
import ru.innopolis.model.ContactType;
import ru.innopolis.view.R;

/**
 * Created by ibrahim on 6/24/2017.
 */

public class ContactListAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contacts;

    public ContactListAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_list_view_row_items, parent, false);
        }

        Contact contact = (Contact) getItem(position);

        TextView txvContactValue = (TextView) convertView.findViewById(R.id.txvContactValue);
        txvContactValue.setText(contact.getValue());

        ImageView img = (ImageView) convertView.findViewById(R.id.imgContactType);
        img.setImageResource(contact.getIcon());

        return convertView;
    }
}
