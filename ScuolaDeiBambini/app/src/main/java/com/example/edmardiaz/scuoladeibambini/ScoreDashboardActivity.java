package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * Created by EdmarDiaz on 12/7/2017.
 */

public class ScoreDashboardActivity extends AppCompatActivity {


    TextView txtNumberScore, txtSchoolScore, txtFoodScore, txtHouseScore, txtPhrasesScore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_dashboard);

        // initialize all the object components
        initializeComponents();

        setScores();

    }

    public void initializeComponents() {
        txtNumberScore = (TextView)findViewById(R.id.txtNumberScore);
        txtSchoolScore = (TextView)findViewById(R.id.txtSchoolScore);
        txtFoodScore = (TextView)findViewById(R.id.txtFoodScore);
        txtHouseScore = (TextView)findViewById(R.id.txtHouseScore);
        txtPhrasesScore = (TextView)findViewById(R.id.txtPhrasesScore);
    }

    public void setScores() {
        SharedPreferences sp = getSharedPreferences("scores", MODE_PRIVATE);
        int number = sp.getInt(getString(R.string.category_name_numeri), -1);
        if(number != -1){
            txtNumberScore.setText(Integer.valueOf(number)+"/5");
        }else{
            txtNumberScore.setText("0/5");
        }
        int food = sp.getInt(getString(R.string.category_name_alimenti), -1);
        if(food != -1){
            txtFoodScore.setText(Integer.valueOf(food)+"/10");
        }else{
            txtFoodScore.setText("0/10");
        }

        int school = sp.getInt(getString(R.string.category_name_scuola), -1);
        if(school != -1){
            txtSchoolScore.setText(Integer.valueOf(school)+"/10");
        }else{
            txtSchoolScore.setText("0/10");
        }

        int house = sp.getInt(getString(R.string.category_name_casa), -1);
        if(house != -1){
            txtHouseScore.setText(Integer.valueOf(house)+"/10");
        }else{
            txtHouseScore.setText("0/10");
        }

        int phrases = sp.getInt(getString(R.string.category_name_phrase), -1);
        if(phrases != -1){
            txtPhrasesScore.setText(Integer.valueOf(phrases)+"/10");
        }else{
            txtPhrasesScore.setText("0/10");
        }

    }

    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(ScoreDashboardActivity.this, CategoryActivity.class));
    }
}
