package com.example.garagetestproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garagetestproject.App;
import com.example.garagetestproject.R;
import com.example.garagetestproject.activity.ContactInfoActivity;
import com.example.garagetestproject.model.Contact;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

public class Adapter extends RecyclerView.Adapter<Adapter.ContactViewHolder> {

    private SortedList<Contact> mContactsList;

    public Adapter() {

        mContactsList = new SortedList<>(Contact.class, new SortedList.Callback<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                return 0;
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Contact oldItem, Contact newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Contact item1, Contact item2) {
                return item1.getUid() == item2.getUid();
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {

            }
        });
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mFirstNameTextView;
        TextView mLastNameTextView;
        TextView mEmailTextView;
        View mDeleteButton;

        public ContactViewHolder(@NonNull final View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mFirstNameTextView = itemView.findViewById(R.id.firstNameTextView);
            mLastNameTextView = itemView.findViewById(R.id.lastNameTextView);
            mEmailTextView = itemView.findViewById(R.id.emailTextView);
            mDeleteButton = itemView.findViewById(R.id.deleteImageView);

            mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.getInstance().getContactDao().delete(mContactsList.get(getAdapterPosition()));
                    Toast.makeText(v.getContext(), "Delete View Clicked", Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Contact clickedDataItem = mContactsList.get(pos);
                        Intent intent = new Intent(v.getContext(), ContactInfoActivity.class);
                        intent.putExtra("Contact", clickedDataItem);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.mFirstNameTextView.setText(mContactsList.get(position).getFirstName());
        holder.mLastNameTextView.setText(mContactsList.get(position).getLastName());
        holder.mEmailTextView.setText(mContactsList.get(position).getEmail());
        holder.mImageView.setImageResource(R.drawable.defavatar);
    }

    @Override
    public int getItemCount() {
        return mContactsList.size();
    }

    public void setItems(List<Contact> list) {
        mContactsList.replaceAll(list);
    }
}