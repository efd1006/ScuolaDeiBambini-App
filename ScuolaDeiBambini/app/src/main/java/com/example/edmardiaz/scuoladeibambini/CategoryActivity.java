package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.edmardiaz.scuoladeibambini.SelectedCategoryActivity;

import me.drakeet.materialdialog.MaterialDialog;

public class CategoryActivity extends AppCompatActivity {

    ImageButton btn_school, btn_number, btn_food, btn_house, btn_search, btn_phrase;
    boolean isSearching = false;
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
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // handle button alphabet event
        btn_school = (ImageButton)findViewById(R.id.btn_school);
        btn_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected(getString(R.string.category_name_scuola));
            }
        });

        // handle button number event
        btn_number = (ImageButton)findViewById(R.id.btn_number);
        btn_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected(getString(R.string.category_name_numeri));
            }
        });

        // handle button number event
        btn_food = (ImageButton)findViewById(R.id.btn_food);
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected(getString(R.string.category_name_alimenti));
            }
        });

        // handle button house event
        btn_house = (ImageButton)findViewById(R.id.btn_house);
        btn_house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next activity with @param category_name
                goToSelected(getString(R.string.category_name_casa));
            }
        });

        // handle button phrase
        btn_phrase = (ImageButton)findViewById(R.id.btn_phrase);
        btn_phrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSelected(getString(R.string.category_name_phrase));
            }
        });

        // handle button search
        btn_search = (ImageButton)findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSearching = true;
                showDialog();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.home:
                this.finish();
                break;
            case R.id.viewGrade:
                Intent i = new Intent(CategoryActivity.this, ScoreDashboardActivity.class);
                startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void goToSelected(String category_name) {
        if(!category_name.equals(getString(R.string.category_name_phrase))){
            Intent intent = new Intent(CategoryActivity.this, SelectedCategoryActivity.class);
            intent.putExtra("category_name", category_name);
            intent.putExtra("isSearching", isSearching);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
        }else{
            Intent intentt = new Intent(CategoryActivity.this, PhraseActivity.class);
            startActivity(intentt);
            overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
        }
    }
    // show dialog
    public void showDialog() {
        final EditText contentView = new EditText(this);
        final MaterialDialog dialog = new MaterialDialog(this);
        dialog.setTitle("Enter Word in English")
            .setContentView(contentView)
            .setPositiveButton("Search", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, SelectedCategoryActivity.class);
                intent.putExtra("category_name", "Search Result");
                intent.putExtra("isSearching", isSearching);
                intent.putExtra("keyword", contentView.getText().toString());
                startActivity(intent);
                overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
                }
            })
            .setNegativeButton("Cancel", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isSearching = false;
                    dialog.dismiss();
                }
            });
        dialog.show();
    }
}
