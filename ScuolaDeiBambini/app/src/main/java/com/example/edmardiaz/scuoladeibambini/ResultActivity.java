package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by EdmarDiaz on 11/10/2017.
 */

public class ResultActivity extends AppCompatActivity {

    LinearLayout l1,l2;
    Button btn_submit;
    TextView txt_score, txt_remarks;
    Animation uptodown, downtoup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        // initialize the components
        initializeComponents();

        //load animation
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        // set animation to linear layouts
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);

        // get intent
        Intent getIntent = getIntent();
        String remarks = getIntent.getStringExtra("remarks");
        String score = getIntent.getStringExtra("score");
        txt_remarks.setText(remarks);
        txt_score.setText(score);
    }

    public void initializeComponents() {
        btn_submit = (Button)findViewById(R.id.btn_submit);
        l1 = (LinearLayout)findViewById(R.id.linear_header);
        l2 = (LinearLayout)findViewById(R.id.linear_footer);
        txt_score = (TextView)findViewById(R.id.txt_score);
        txt_remarks = (TextView)findViewById(R.id.txt_remarks);
    }
}
