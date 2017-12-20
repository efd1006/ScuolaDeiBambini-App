package com.example.edmardiaz.scuoladeibambini;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by EdmarDiaz on 12/21/2017.
 */

public class PhraseActivity extends AppCompatActivity {

    ArrayList<String> it_phrases = new ArrayList<>();
    ArrayList<String> en_phrases = new ArrayList<>();
    int counter = 0;
    MediaPlayer mp;
    //widgets
    TextView txtEnglish, txtItalian;
    ImageButton btn_back,btn_next,btn_listen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phrase_category_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        toolbar.setTitle("FRASE");
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        // setup back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // load words
        txtEnglish = (TextView)findViewById(R.id.txtEnglish);
        txtItalian = (TextView)findViewById(R.id.txtItalian);
        btn_back = (ImageButton)findViewById(R.id.btn_cat_back);
        btn_next = (ImageButton)findViewById(R.id.btn_next);
        btn_listen = (ImageButton)findViewById(R.id.btn_listen);
        Phrases p = new Phrases();
        en_phrases = p.getEn_phrases();
        it_phrases = p.getIt_phrases();
        txtEnglish.setText(en_phrases.get(0));
        txtItalian.setText(it_phrases.get(0));

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < en_phrases.size() - 1) {
                    counter = counter + 1;
                    changeView(counter);
                }else{
                    return;
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter > 0) {
                    counter = counter - 1;
                    changeView(counter);
                } else {
                    counter = 0;
                    changeView(counter);
                }
            }
        });

        btn_listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen(counter);
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

    public void changeView(int c){
        txtEnglish.setText(en_phrases.get(c));
        txtItalian.setText(it_phrases.get(c));
    }

    public void listen(int counter) {
        if (counter == 0) {
            mp = MediaPlayer.create(this, R.raw.goodmorning);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 1) {
            mp = MediaPlayer.create(this, R.raw.goodafternoon);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 2) {
            mp = MediaPlayer.create(this, R.raw.howareyou);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 3) {
            mp = MediaPlayer.create(this, R.raw.whatisyourname);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 4) {
            mp = MediaPlayer.create(this, R.raw.nicetomeetyou);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 5) {
            mp = MediaPlayer.create(this, R.raw.thankyou);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 6) {
            mp = MediaPlayer.create(this, R.raw.yourewelcome);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 7) {
            mp = MediaPlayer.create(this, R.raw.mynameis);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 8) {
            mp = MediaPlayer.create(this, R.raw.howoldareyou);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        } else if (counter == 9) {
            mp = MediaPlayer.create(this, R.raw.seeyoulater);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
    }
}
