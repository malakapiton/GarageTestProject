package com.example.garagetestproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.garagetestproject.App;
import com.example.garagetestproject.R;
import com.example.garagetestproject.model.Contact;


public class ContactInfoActivity extends AppCompatActivity {

    private EditText mFirstNameEditText;
    private EditText mLastNameEditText;
    private EditText mEmailEditText;

    Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirstNameEditText = findViewById(R.id.first_name_edit_text);
        mLastNameEditText = findViewById(R.id.last_name_edit_text);
        mEmailEditText = findViewById(R.id.email_edit_text);

        if (getIntent().hasExtra("Contact")) {
            mContact = getIntent().getParcelableExtra("Contact");
            mFirstNameEditText.setText(mContact.getFirstName());
            mLastNameEditText.setText(mContact.getLastName());
            mEmailEditText.setText(mContact.getEmail());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save_changes:
                mContact.setFirstName(mFirstNameEditText.getText().toString());
                mContact.setLastName(mLastNameEditText.getText().toString());
                mContact.setEmail(mEmailEditText.getText().toString());
                if (getIntent().hasExtra("Contact")) {
                    App.getInstance().getContactDao().update(mContact);
                }
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
