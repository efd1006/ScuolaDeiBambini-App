package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by EdmarDiaz on 27/09/2017.
 */

public class SelectedCategoryActivity extends AppCompatActivity {

    ImageView img_header, img_italian, img_english;
    ImageButton btn_next, btn_back, btn_play, btn_activity;
    ArrayList<Integer> main_image = new ArrayList<>();
    ArrayList<Integer> english = new ArrayList<>();
    ArrayList<Integer> italian = new ArrayList<>();
    int counter = 0;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fullscreen
        /*
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        */
        // load the layout
        setContentView(R.layout.activity_selected_category);

        // get the intent passed value
        Intent i = getIntent();
        String category_name = i.getStringExtra("category_name");

        // setup the toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        toolbar.setTitle(category_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        // setup back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // getImages
        main_image = loadImage(category_name);
        english = loadEnglishEquivalent(category_name);
        italian = loadItalianEquivalent(category_name);

        // make a nest instance of imageviews and set view to index 0
        img_header = (ImageView)findViewById(R.id.img_container);
        img_english = (ImageView)findViewById(R.id.img_english);
        img_italian = (ImageView)findViewById(R.id.img_italian);
        changeView(0);
        listen(0);

        // handle next button event
        btn_next = (ImageButton)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter < 10) {
                    counter = counter + 1;
                    changeView(counter);
                    listen(counter);
                }else {
                    showDialog();
                }
            }
        });

        // handle listen button
        btn_play = (ImageButton)findViewById(R.id.btn_listen);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen(counter);
            }
        });

        // handle back button event
        btn_back = (ImageButton)findViewById(R.id.btn_cat_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter > 0){
                    counter = counter - 1;
                    changeView(counter);
                    listen(counter);
                }else {
                    counter = 0;
                    changeView(counter);
                    listen(counter);
                }
            }
        });

        // handle activity button
        btn_activity = (ImageButton)findViewById(R.id.btn_activity);
        btn_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectedCategoryActivity.this, AssessmentActivity.class);
                Intent i = getIntent();
                String c = i.getStringExtra("category_name");
                intent.putExtra("category_name", c);
                intent.putExtra("imageId", main_image);
                startActivity(intent);
                finish();
                overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
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

    // show dialog
    public void showDialog() {
        final MaterialDialog dialog = new MaterialDialog(this);
                dialog.setTitle("Are you ready to take the activity?")
                .setPositiveButton("Start Activity", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SelectedCategoryActivity.this, AssessmentActivity.class);
                        Intent i = getIntent();
                        String c = i.getStringExtra("category_name");
                        intent.putExtra("category_name", c);
                        intent.putExtra("imageId", main_image);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
                    }
                })
                .setNegativeButton("No", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    // get english equivalent
    public ArrayList<Integer> loadEnglishEquivalent (String category_name) {

        ArrayList<Integer> imageId = new ArrayList<>();

        if(category_name.equals("NUMERI")) {
            imageId.add(R.drawable.en_zero);
            imageId.add(R.drawable.en_one);
            imageId.add(R.drawable.en_two);
            imageId.add(R.drawable.en_three);
            imageId.add(R.drawable.en_four);
            imageId.add(R.drawable.en_five);
            imageId.add(R.drawable.en_six);
            imageId.add(R.drawable.en_seven);
            imageId.add(R.drawable.en_eight);
            imageId.add(R.drawable.en_nine);
            imageId.add(R.drawable.en_ten);
        }

        return imageId;
    }

    // get italian equivalent
    public ArrayList<Integer> loadItalianEquivalent (String category_name) {

        ArrayList<Integer> imageId = new ArrayList<>();

        if(category_name.equals("NUMERI")) {
            imageId.add(R.drawable.it_zero);
            imageId.add(R.drawable.it_one);
            imageId.add(R.drawable.it_two);
            imageId.add(R.drawable.it_three);
            imageId.add(R.drawable.it_four);
            imageId.add(R.drawable.it_five);
            imageId.add(R.drawable.it_six);
            imageId.add(R.drawable.it_seven);
            imageId.add(R.drawable.it_eight);
            imageId.add(R.drawable.it_nine);
            imageId.add(R.drawable.it_ten);
        }

        return imageId;
    }

    // get the main image
    public ArrayList<Integer> loadImage(String category_name) {

        ArrayList<Integer> imageId = new ArrayList<>();

        if(category_name.equals("NUMERI")) {
            imageId.add(R.drawable.zero);
            imageId.add(R.drawable.one);
            imageId.add(R.drawable.two);
            imageId.add(R.drawable.three);
            imageId.add(R.drawable.four);
            imageId.add(R.drawable.five);
            imageId.add(R.drawable.six);
            imageId.add(R.drawable.seven);
            imageId.add(R.drawable.eight);
            imageId.add(R.drawable.nine);
            imageId.add(R.drawable.ten);
        }

        return imageId;
    }

    // change imageviews dynamically
    public void changeView(int counter) {

        img_header.setImageResource(main_image.get(counter));
        img_english.setImageResource(english.get(counter));
        img_italian.setImageResource(italian.get(counter));
    }

    // get audio
    public ArrayList<Integer> loadAudio(String category_name) {

        ArrayList<Integer> audioId = new ArrayList<>();

        if(category_name.equals("NUMERI")) {
            audioId.add(R.raw.zero);
            audioId.add(R.raw.one);
            audioId.add(R.raw.two);
            audioId.add(R.raw.three);
            audioId.add(R.raw.four);
            audioId.add(R.raw.five);
            audioId.add(R.raw.six);
            audioId.add(R.raw.seven);
            audioId.add(R.raw.eight);
            audioId.add(R.raw.nine);
            audioId.add(R.raw.ten);
        }

        return audioId;
    }

    // play the audio
    public void listen(int counter) {

        if(counter == 0) {
            mp = MediaPlayer.create(this,R.raw.zero);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 1) {
            mp = MediaPlayer.create(this,R.raw.one);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 2) {
            mp = MediaPlayer.create(this,R.raw.two);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 3) {
            mp = MediaPlayer.create(this,R.raw.three);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 4) {
            mp = MediaPlayer.create(this,R.raw.four);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 5) {
            mp = MediaPlayer.create(this,R.raw.five);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 6) {
            mp = MediaPlayer.create(this,R.raw.six);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 7) {
            mp = MediaPlayer.create(this,R.raw.seven);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 8) {
            mp = MediaPlayer.create(this,R.raw.eight);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 9) {
            mp = MediaPlayer.create(this,R.raw.nine);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (counter == 10) {
            mp = MediaPlayer.create(this,R.raw.ten);
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
