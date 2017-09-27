package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.edmardiaz.scuoladeibambini.SelectedCategoryActivity;

public class CategoryActivity extends AppCompatActivity {

    ImageButton btn_alphabet, btn_number, btn_food, btn_house;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // full screen with no top bar
        /*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */
        setContentView(R.layout.activity_category);

        // toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        toolbar.setTitle("SELEZIONA  UNA CATEGORIA");
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        // setup back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // handle button alphabet event
        btn_alphabet = (ImageButton)findViewById(R.id.btn_alphabet);
        btn_alphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected("ALFABETO");
            }
        });

        // handle button number event
        btn_number = (ImageButton)findViewById(R.id.btn_number);
        btn_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected("NUMERI");
            }
        });

        // handle button number event
        btn_food = (ImageButton)findViewById(R.id.btn_food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected("ALIMENTI");
            }
        });

        // handle button house event
        btn_house = (ImageButton)findViewById(R.id.btn_house);
        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected("PARTI DI CASA");
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

    public void goToSelected(String category_name) {
        Intent intent = new Intent(CategoryActivity.this, SelectedCategoryActivity.class);
        intent.putExtra("category_name", category_name);
        startActivity(intent);
        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);

    }
}
