package com.example.edmardiaz.scuoladeibambini;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AssessmentActivity extends AppCompatActivity {

    RadioButton radio_a, radio_b, radio_c, radio_d;
    ImageButton btn_a, btn_b, btn_c, btn_d, btn_back, btn_next, btn_en, btn_it;
    Button btn_1, btn_2, btn_3;
    ImageView img_container, instruction_1, instruction_2;
    ArrayList<Integer> imageId = new ArrayList<>();
    ArrayList<Integer> choices = new ArrayList<>();
    MediaPlayer mp;
    int index;
    int score = 0;
    int question_counter = 0;
    String state = "EN";
    Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        // initialize components
        initializeComponents();
        // get category name
        in = getIntent();
        String category_name = "Attività "+ in.getStringExtra("category_name");

        // handle radiobutton behavior
        changeState();

        // hide back button at initial state
        btn_back.setVisibility(View.INVISIBLE);

        // setup the toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        toolbar.setTitle(category_name);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);

        // setup back button on toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // handle EN button event
        btn_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instruction_1.setImageResource(R.drawable.instruction_1);
                instruction_2.setImageResource(R.drawable.instruction_2);
                state = "EN";
            }
        });

        // handle IT button event
        btn_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                instruction_1.setImageResource(R.drawable.it_instruction_1);
                instruction_2.setImageResource(R.drawable.it_instruction_2);
                state = "IT";
            }
        });

        initializeChoices(in.getStringExtra("category_name"));
    }

    // initialize type of choice
    public void initializeChoices(String category_name) {
        if(category_name.equals(getString(R.string.category_name_numeri))){
            // populate the view layout
            imageId = in.getIntegerArrayListExtra("imageId");
            index = getRandomNumber(0,imageId.size()-1);
            choices.add(index);
            makeChoices();
            Collections.shuffle(choices);
            changeView(index, imageId);
            showListenButton();
            // handle btn_a event
            btn_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listen(choices.get(0));
                }
            });
            // handle btn_b event
            btn_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listen(choices.get(1));
                }
            });
            // handle btn_c event
            btn_c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listen(choices.get(2));
                }
            });
            // handle btn_d event
            btn_d.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listen(choices.get(3));
                }
            });

            // handle button next event
            btn_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(question_counter != (imageId.size()/2)-1) {
                        if(radio_a.isChecked()){
                            checkAnswer(choices.get(0));
                        }else if (radio_b.isChecked()){
                            checkAnswer(choices.get(1));
                        }else if (radio_c.isChecked()){
                            checkAnswer(choices.get(2));
                        }else if(radio_d.isChecked()){
                            checkAnswer(choices.get(3));
                        }else{
                            if(state.equals("EN")){
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(getApplicationContext(), "Please Select An Answer", duration);
                                toast.show();
                            }else if (state.equals("IT")){
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(getApplicationContext(), "Per Favore Seleziona Una Risposta", duration);
                                toast.show();
                            }
                            return;
                        }
                        choices.clear();
                        resetRadioCheck();
                        index = getRandomNumber(0, imageId.size()-1);
                        choices.add(index);
                        makeChoices();
                        Collections.shuffle(choices);
                        changeView(index, imageId);
                        question_counter++;
                        //btn_back.setVisibility(View.VISIBLE);
                    }else{
                        return;
                    }
                }
            });
        }else {
            hideListenButton();
        }
    }

    // make the listen button invinsible
    public void hideListenButton() {
        btn_a.setVisibility(View.GONE);
        btn_b.setVisibility(View.GONE);
        btn_c.setVisibility(View.GONE);
        btn_d.setVisibility(View.GONE);
        radio_a.setVisibility(View.INVISIBLE);
        radio_b.setVisibility(View.INVISIBLE);
        radio_c.setVisibility(View.INVISIBLE);
        radio_d.setVisibility(View.INVISIBLE);

        btn_1.setVisibility(View.VISIBLE);
        btn_2.setVisibility(View.VISIBLE);
        btn_3.setVisibility(View.VISIBLE);

    }

    // make the listen button visible
    public void showListenButton() {
        btn_a.setVisibility(View.VISIBLE);
        btn_b.setVisibility(View.VISIBLE);
        btn_c.setVisibility(View.VISIBLE);
        btn_d.setVisibility(View.VISIBLE);
        radio_a.setVisibility(View.VISIBLE);
        radio_b.setVisibility(View.VISIBLE);
        radio_c.setVisibility(View.VISIBLE);
        radio_d.setVisibility(View.VISIBLE);

        btn_1.setVisibility(View.INVISIBLE);
        btn_2.setVisibility(View.INVISIBLE);
        btn_3.setVisibility(View.INVISIBLE);
    }

    // uncheck all the radiobutton
    public void resetRadioCheck(){
        radio_a.setChecked(false);
        radio_b.setChecked(false);
        radio_c.setChecked(false);
        radio_d.setChecked(false);
    }

    //generate random choices
    public void makeChoices() {
        int x = getRandomNumber(0,imageId.size()-1);

        for(int i=0;i<3;i++) {
            if(!choices.contains(x)){
                choices.add(x);
            }else{
                do {
                    x = getRandomNumber(0,imageId.size()-1);
                } while(index == x || choices.contains(x));
                choices.add(x);
            }
        }
    }

    // generate random number within range
    public int getRandomNumber(int min, int max) {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    // change the view of img_container
    public void changeView(int index, ArrayList<Integer> imageId) {
        img_container.setImageResource(imageId.get(index));
    }

    public void initializeComponents() {
        // initialize the buttons
        radio_a = (RadioButton)findViewById(R.id.radio_a);
        btn_a = (ImageButton)findViewById(R.id.btn_a);
        radio_b = (RadioButton)findViewById(R.id.radio_b);
        btn_b = (ImageButton)findViewById(R.id.btn_b);
        radio_c = (RadioButton)findViewById(R.id.radio_c);
        btn_c = (ImageButton)findViewById(R.id.btn_c);
        radio_d = (RadioButton)findViewById(R.id.radio_d);
        btn_d = (ImageButton)findViewById(R.id.btn_d);
        btn_back = (ImageButton)findViewById(R.id.btn_back);
        btn_next = (ImageButton)findViewById(R.id.btn_next);
        btn_en = (ImageButton)findViewById(R.id.btn_en);
        btn_it = (ImageButton)findViewById(R.id.btn_it);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        // initialize the imageview
        img_container = (ImageView)findViewById(R.id.img_container);
        instruction_1 = (ImageView)findViewById(R.id.instruction_1);
        instruction_2 = (ImageView)findViewById(R.id.instruction_2);
    }

    // check the correct answer
    public void checkAnswer(int correct_ans) {
        if(correct_ans == index) {
            score++;
        }
    }

    // event handler for radio button events when clicked
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
            choices.clear();
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // play the audio
    public void listen(int index) {

        if(index == 0) {
            mp = MediaPlayer.create(this,R.raw.zero);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 1) {
            mp = MediaPlayer.create(this,R.raw.one);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 2) {
            mp = MediaPlayer.create(this,R.raw.two);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 3) {
            mp = MediaPlayer.create(this,R.raw.three);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 4) {
            mp = MediaPlayer.create(this,R.raw.four);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index  == 5) {
            mp = MediaPlayer.create(this,R.raw.five);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 6) {
            mp = MediaPlayer.create(this,R.raw.six);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 7) {
            mp = MediaPlayer.create(this,R.raw.seven);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 8) {
            mp = MediaPlayer.create(this,R.raw.eight);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 9) {
            mp = MediaPlayer.create(this,R.raw.nine);
            mp.start();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }else if (index == 10) {
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
