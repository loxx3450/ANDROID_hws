package com.loxx3450.hw_31_03_25.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.loxx3450.hw_31_03_25.R;
import com.loxx3450.hw_31_03_25.SecondActivity;
import com.loxx3450.hw_31_03_25.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private final List<Contact> contacts;
    private final ActivityResultLauncher<Intent> updateLauncher;

    public ContactAdapter(List<Contact> contacts, ActivityResultLauncher<Intent> updateLauncher) {
        this.contacts = contacts;
        this.updateLauncher = updateLauncher;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.contact_item, parent, false);

        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);

        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhone());
        holder.updateButton.setOnClickListener((v) -> updateContact(holder, contact, position));
        holder.deleteButton.setOnClickListener((v) -> removeContact(position));
    }

    private void updateContact(ContactViewHolder holder, Contact contact, int position) {
        Intent intent = new Intent(holder.itemView.getContext(), SecondActivity.class);

        intent.putExtra("position", position);
        intent.putExtra("name", contact.getName());
        intent.putExtra("phone", contact.getPhone());

        updateLauncher.launch(intent);
    }

    private void removeContact(int position) {
        contacts.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, contacts.size());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView phoneTextView;
        private final Button updateButton;
        private final Button deleteButton;

        public ContactViewHolder(View view) {
            super(view);

            nameTextView = view.findViewById(R.id.nameTextView);
            phoneTextView = view.findViewById(R.id.phoneTextView);
            updateButton = view.findViewById(R.id.updateButton);
            deleteButton = view.findViewById(R.id.deleteButton);
        }
    }
}
