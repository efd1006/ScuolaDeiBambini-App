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
import java.util.logging.Handler;

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
        final String category_name = i.getStringExtra("category_name");

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
        System.out.println(main_image.size());
        english = loadEnglishEquivalent(category_name);
        italian = loadItalianEquivalent(category_name);

        // make a nest instance of imageviews and set view to index 0
        img_header = (ImageView)findViewById(R.id.img_container);
        img_english = (ImageView)findViewById(R.id.img_english);
        img_italian = (ImageView)findViewById(R.id.img_italian);
        changeView(0);

        listen(0,category_name);

        // handle next button event
        btn_next = (ImageButton)findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter < main_image.size()-1) {
                    counter = counter + 1;
                    changeView(counter);
                    listen(counter,category_name);
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
                listen(counter,category_name);
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
                    listen(counter,category_name);
                }else {
                    counter = 0;
                    changeView(counter);
                    listen(counter,category_name);
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

        if(category_name.equals(getString(R.string.category_name_numeri))) {
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
        }else if(category_name.equals(getString(R.string.category_name_alimenti))) {
            imageId.add(R.drawable.en_banana);
            imageId.add(R.drawable.en_bread);
            imageId.add(R.drawable.en_cake);
            imageId.add(R.drawable.en_cheese);
            imageId.add(R.drawable.en_eggs);
            imageId.add(R.drawable.en_milk);
            imageId.add(R.drawable.en_steak);
            imageId.add(R.drawable.en_rice);
            imageId.add(R.drawable.en_sandwich);
            imageId.add(R.drawable.en_strawberry);
        }else if(category_name.equals(getString(R.string.category_name_scuola))) {
            imageId.add(R.drawable.en_bag);
            imageId.add(R.drawable.en_book);
            imageId.add(R.drawable.en_chair);
            imageId.add(R.drawable.en_eraser);
            imageId.add(R.drawable.en_microscope);
            imageId.add(R.drawable.en_notebook);
            imageId.add(R.drawable.en_pen);
            imageId.add(R.drawable.en_pencil);
            imageId.add(R.drawable.en_pencilcase);
            imageId.add(R.drawable.en_scissors);
            imageId.add(R.drawable.en_sharpener);
        }else if(category_name.equals(getString(R.string.category_name_casa))) {
            imageId.add(R.drawable.en_bathroom);
            imageId.add(R.drawable.en_bedroom);
            imageId.add(R.drawable.en_dinningroom);
            imageId.add(R.drawable.en_garage);
            imageId.add(R.drawable.en_garden);
            imageId.add(R.drawable.en_hall);
            imageId.add(R.drawable.en_house);
            imageId.add(R.drawable.en_kitchen);
            imageId.add(R.drawable.en_livingroom);
            imageId.add(R.drawable.en_toilet);
        }

        return imageId;
    }

    // get italian equivalent
    public ArrayList<Integer> loadItalianEquivalent (String category_name) {

        ArrayList<Integer> imageId = new ArrayList<>();

        if(category_name.equals(getString(R.string.category_name_numeri))) {
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
        }else if(category_name.equals(getString(R.string.category_name_alimenti))) {
            imageId.add(R.drawable.it_banana);
            imageId.add(R.drawable.it_bread);
            imageId.add(R.drawable.it_cake);
            imageId.add(R.drawable.it_cheese);
            imageId.add(R.drawable.it_eggs);
            imageId.add(R.drawable.it_milk);
            imageId.add(R.drawable.it_steak);
            imageId.add(R.drawable.it_rice);
            imageId.add(R.drawable.it_sandwich);
            imageId.add(R.drawable.it_strawberry);
        }else if(category_name.equals(getString(R.string.category_name_scuola))) {
            imageId.add(R.drawable.it_bag);
            imageId.add(R.drawable.it_book);
            imageId.add(R.drawable.it_chair);
            imageId.add(R.drawable.it_eraser);
            imageId.add(R.drawable.it_microscope);
            imageId.add(R.drawable.it_notebook);
            imageId.add(R.drawable.it_pen);
            imageId.add(R.drawable.it_pencil);
            imageId.add(R.drawable.it_pencilcase);
            imageId.add(R.drawable.it_scissors);
            imageId.add(R.drawable.it_sharpener);
        }else if(category_name.equals(getString(R.string.category_name_casa))) {
            imageId.add(R.drawable.it_bathroom);
            imageId.add(R.drawable.it_bedroom);
            imageId.add(R.drawable.it_dinningroom);
            imageId.add(R.drawable.it_garage);
            imageId.add(R.drawable.it_garden);
            imageId.add(R.drawable.it_hall);
            imageId.add(R.drawable.it_house);
            imageId.add(R.drawable.it_kitchen);
            imageId.add(R.drawable.it_livingroom);
            imageId.add(R.drawable.it_toilet);
        }

        return imageId;
    }

    // get the main image
    public ArrayList<Integer> loadImage(String category_name) {

        ArrayList<Integer> imageId = new ArrayList<>();

        if(category_name.equals(getString(R.string.category_name_numeri))) {
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
        }else if(category_name.equals(getString(R.string.category_name_alimenti))) {
            imageId.add(R.drawable.banana);
            imageId.add(R.drawable.bread);
            imageId.add(R.drawable.cake);
            imageId.add(R.drawable.cheese);
            imageId.add(R.drawable.eggs);
            imageId.add(R.drawable.milk);
            imageId.add(R.drawable.pork);
            imageId.add(R.drawable.rice);
            imageId.add(R.drawable.sandwich);
            imageId.add(R.drawable.strawberry);
        }else if(category_name.equals(getString(R.string.category_name_scuola))) {
            imageId.add(R.drawable.bag);
            imageId.add(R.drawable.book);
            imageId.add(R.drawable.chair);
            imageId.add(R.drawable.eraser);
            imageId.add(R.drawable.microscope);
            imageId.add(R.drawable.notebook);
            imageId.add(R.drawable.pen);
            imageId.add(R.drawable.pencil);
            imageId.add(R.drawable.pencilcase);
            imageId.add(R.drawable.scissors);
            imageId.add(R.drawable.sharpener);
        }else if(category_name.equals(getString(R.string.category_name_casa))) {
            imageId.add(R.drawable.bathroom);
            imageId.add(R.drawable.bedroom);
            imageId.add(R.drawable.dinning);
            imageId.add(R.drawable.garage);
            imageId.add(R.drawable.garden);
            imageId.add(R.drawable.hall);
            imageId.add(R.drawable.house);
            imageId.add(R.drawable.kitchen);
            imageId.add(R.drawable.livingroom);
            imageId.add(R.drawable.toilet);
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

        if(category_name.equals(getString(R.string.category_name_numeri))) {
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
        }else if(category_name.equals(getString(R.string.category_name_alimenti))) {
            audioId.add(R.raw.banana);
            audioId.add(R.raw.bread);
            audioId.add(R.raw.cake);
            audioId.add(R.raw.cheese);
            audioId.add(R.raw.eggs);
            audioId.add(R.raw.milk);
            audioId.add(R.raw.pork);
            audioId.add(R.raw.rice);
            audioId.add(R.raw.panino);
            audioId.add(R.raw.strawberry);
        }else if(category_name.equals(getString(R.string.category_name_scuola))) {
            audioId.add(R.raw.bag);
            audioId.add(R.raw.book);
            audioId.add(R.raw.chair);
            audioId.add(R.raw.eraser);
            audioId.add(R.raw.microscope);
            audioId.add(R.raw.notebook);
            audioId.add(R.raw.pen);
            audioId.add(R.raw.pencil);
            audioId.add(R.raw.pencilcase);
            audioId.add(R.raw.scissors);
            audioId.add(R.raw.sharpener);
        }else if(category_name.equals(getString(R.string.category_name_casa))) {
            audioId.add(R.raw.bathroom);
            audioId.add(R.raw.bedroom);
            audioId.add(R.raw.dinningroom);
            audioId.add(R.raw.garage);
            audioId.add(R.raw.garden);
            audioId.add(R.raw.hallway);
            audioId.add(R.raw.house);
            audioId.add(R.raw.kitchen);
            audioId.add(R.raw.livingroom);
            audioId.add(R.raw.toilet);
        }

        return audioId;
    }

    // play the audio
    public void listen(int counter, String category_name) {
        if(category_name.equals(getString(R.string.category_name_numeri))) {
            if (counter == 0) {
                mp = MediaPlayer.create(this, R.raw.zero);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 1) {
                mp = MediaPlayer.create(this, R.raw.one);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 2) {
                mp = MediaPlayer.create(this, R.raw.two);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 3) {
                mp = MediaPlayer.create(this, R.raw.three);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 4) {
                mp = MediaPlayer.create(this, R.raw.four);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 5) {
                mp = MediaPlayer.create(this, R.raw.five);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 6) {
                mp = MediaPlayer.create(this, R.raw.six);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 7) {
                mp = MediaPlayer.create(this, R.raw.seven);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 8) {
                mp = MediaPlayer.create(this, R.raw.eight);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 9) {
                mp = MediaPlayer.create(this, R.raw.nine);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 10) {
                mp = MediaPlayer.create(this, R.raw.ten);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            }
        }else if(category_name.equals(getString(R.string.category_name_alimenti))) {
            if (counter == 0) {
                mp = MediaPlayer.create(this, R.raw.banana);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 1) {
                mp = MediaPlayer.create(this, R.raw.bread);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 2) {
                mp = MediaPlayer.create(this, R.raw.cake);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 3) {
                mp = MediaPlayer.create(this, R.raw.cheese);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 4) {
                mp = MediaPlayer.create(this, R.raw.eggs);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 5) {
                mp = MediaPlayer.create(this, R.raw.milk);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 6) {
                mp = MediaPlayer.create(this, R.raw.pork);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 7) {
                mp = MediaPlayer.create(this, R.raw.rice);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 8) {
                mp = MediaPlayer.create(this, R.raw.panino);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 9) {
                mp = MediaPlayer.create(this, R.raw.strawberry);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            }
        }else if(category_name.equals(getString(R.string.category_name_scuola))) {
            if (counter == 0) {
                mp = MediaPlayer.create(this, R.raw.bag);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 1) {
                mp = MediaPlayer.create(this, R.raw.book);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 2) {
                mp = MediaPlayer.create(this, R.raw.chair);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 3) {
                mp = MediaPlayer.create(this, R.raw.eraser);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 4) {
                mp = MediaPlayer.create(this, R.raw.microscope);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 5) {
                mp = MediaPlayer.create(this, R.raw.notebook);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 6) {
                mp = MediaPlayer.create(this, R.raw.pen);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 7) {
                mp = MediaPlayer.create(this, R.raw.pencil);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 8) {
                mp = MediaPlayer.create(this, R.raw.pencilcase);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 9) {
                mp = MediaPlayer.create(this, R.raw.scissors);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 10) {
                mp = MediaPlayer.create(this, R.raw.sharpener);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            }
        }else if(category_name.equals(getString(R.string.category_name_casa))) {
            if (counter == 0) {
                mp = MediaPlayer.create(this, R.raw.bathroom);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 1) {
                mp = MediaPlayer.create(this, R.raw.bedroom);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 2) {
                mp = MediaPlayer.create(this, R.raw.dinningroom);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 3) {
                mp = MediaPlayer.create(this, R.raw.garage);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 4) {
                mp = MediaPlayer.create(this, R.raw.garden);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 5) {
                mp = MediaPlayer.create(this, R.raw.hallway);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 6) {
                mp = MediaPlayer.create(this, R.raw.house);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 7) {
                mp = MediaPlayer.create(this, R.raw.kitchen);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 8) {
                mp = MediaPlayer.create(this, R.raw.livingroom);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.release();
                    }
                });
            } else if (counter == 9) {
                mp = MediaPlayer.create(this, R.raw.toilet);
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
}
