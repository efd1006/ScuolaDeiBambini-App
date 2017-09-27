package com.example.edmardiaz.scuoladeibambini;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by EdmarDiaz on 26/09/2017.
 */

public class SplashScreen extends AppCompatActivity {
    private int sleep_timer = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // full screen with no top bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        logoLauncher logoLauncher = new logoLauncher();
        logoLauncher.start();
    }

    private class logoLauncher extends Thread {
        public void run() {
            try {
                sleep(1000 * sleep_timer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(SplashScreen.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.transition.slide_in, R.transition.slide_out);
            SplashScreen.this.finish();
        }
    }
}
