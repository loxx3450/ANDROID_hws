package com.loxx3450.hw_07_04_25.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.loxx3450.hw_07_04_25.R;
import com.loxx3450.hw_07_04_25.model.Contact;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
        void onContactDelete(Contact contact);
    }

    private final Context context;
    private final List<Contact> contacts;
    private final OnContactClickListener listener;

    public ContactAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;

        if (context instanceof OnContactClickListener) {
            listener = (OnContactClickListener) context;
        } else {
            throw new RuntimeException("OnContactClickListener is not implemented");
        }
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
        ContactViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contact_card, parent, false);

            viewHolder = new ContactViewHolder();
            viewHolder.layout = convertView.findViewById(R.id.contactCardLayout);
            viewHolder.nameTextView = convertView.findViewById(R.id.nameTextView);
            viewHolder.phoneTextView = convertView.findViewById(R.id.phoneTextView);
            viewHolder.emailTextView = convertView.findViewById(R.id.emailtextView);
            viewHolder.deleteButton = convertView.findViewById(R.id.deleteButton);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ContactViewHolder) convertView.getTag();
        }

        Contact contact = (Contact) getItem(position);

        viewHolder.nameTextView.setText(contact.getName());
        viewHolder.phoneTextView.setText(contact.getPhone());
        viewHolder.emailTextView.setText(contact.getEmail());

        viewHolder.layout.setOnClickListener(v -> listener.onContactClick(contact));
        viewHolder.deleteButton.setOnClickListener(v -> listener.onContactDelete(contact));

        return convertView;
    }

    static class ContactViewHolder {
        ConstraintLayout layout;
        TextView nameTextView;
        TextView phoneTextView;
        TextView emailTextView;
        Button deleteButton;
    }
}
