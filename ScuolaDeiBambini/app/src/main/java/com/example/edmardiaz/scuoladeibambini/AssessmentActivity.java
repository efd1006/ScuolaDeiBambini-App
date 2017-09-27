package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;

public class AssessmentActivity extends AppCompatActivity {

    RadioButton radio_a, radio_b, radio_c, radio_d;
    ImageButton btn_a, btn_b, btn_c, btn_d, btn_back, btn_next, btn_en, btn_it;
    ImageView img_container, instruction_1, instruction_2;
    ArrayList<Integer> imageId = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        // initialize components
        initializeComponents();

        // handle radiobutton behavior
        changeState();

        // hide back button at initial state
        btn_back.setVisibility(View.INVISIBLE);

        // handle EN button event
        btn_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instruction_1.setImageResource(R.drawable.instruction_1);
                instruction_2.setImageResource(R.drawable.instruction_2);
            }
        });

        // handle IT button event
        btn_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instruction_1.setImageResource(R.drawable.it_instruction_1);
                instruction_2.setImageResource(R.drawable.it_instruction_2);
            }
        });

        // get category name
        Intent in = getIntent();
        String category_name = "Attività "+ in.getStringExtra("category_name");
        imageId = in.getIntegerArrayListExtra("imageId");
        int index = getRandomNumber(0,9);
        changeView(index, imageId);
        // setup the toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        toolbar.setTitle(category_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        // setup back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public int getRandomNumber(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    // change the view of img_container
    public void changeView(int index, ArrayList<Integer> imageId) {
        img_container.setImageResource(imageId.get(index));
    }

    public void initializeComponents() {
        radio_a = (RadioButton)findViewById(R.id.radio_a);
        radio_b = (RadioButton)findViewById(R.id.radio_b);
        radio_c = (RadioButton)findViewById(R.id.radio_c);
        radio_d = (RadioButton)findViewById(R.id.radio_d);
        btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_en = (ImageButton)findViewById(R.id.btn_en);
        btn_it = (ImageButton)findViewById(R.id.btn_it);
        img_container = (ImageView)findViewById(R.id.img_container);
        instruction_1 = (ImageView)findViewById(R.id.instruction_1);
        instruction_2 = (ImageView)findViewById(R.id.instruction_2);
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
