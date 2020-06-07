package com.example.garagetestproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.garagetestproject.App;
import com.example.garagetestproject.R;
import com.example.garagetestproject.adapter.Adapter;
import com.example.garagetestproject.model.Contact;

import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mRecyclerView = findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new Adapter();
        mRecyclerView.setAdapter(mAdapter);

        ContactListViewModel contactListViewModel = ViewModelProviders.of(this).get(ContactListViewModel.class);
        contactListViewModel.getContactLiveData().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> list) {
                mAdapter.setItems(list);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_list:
                App.getInstance().getContactDao().deleteAll();
                App.getInstance().createList();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}