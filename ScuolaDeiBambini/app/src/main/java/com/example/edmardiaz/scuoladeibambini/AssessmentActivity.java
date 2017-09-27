package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class AssessmentActivity extends AppCompatActivity {

    RadioButton radio_a, radio_b, radio_c, radio_d;
    String category_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        // handle radiobutton behavior
        radio_a = (RadioButton)findViewById(R.id.radio_a);
        radio_b = (RadioButton)findViewById(R.id.radio_b);
        radio_c = (RadioButton)findViewById(R.id.radio_c);
        radio_d = (RadioButton)findViewById(R.id.radio_d);
        changeState();

        // get category name
        Intent in = getIntent();
        category_name = in.getStringExtra("category_name");

        // setup the toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        toolbar.setTitle(category_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        // setup back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public void changeState() {
        // radio_a active
        radio_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_a.setChecked(true);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_d.setChecked(false);
            }
        });

        // radio b active
        radio_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_a.setChecked(false);
                radio_b.setChecked(true);
                radio_c.setChecked(false);
                radio_d.setChecked(false);
            }
        });

        // radio c active
        radio_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(true);
                radio_d.setChecked(false);
            }
        });

        // radio d active
        radio_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_a.setChecked(false);
                radio_b.setChecked(false);
                radio_c.setChecked(false);
                radio_d.setChecked(true);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
