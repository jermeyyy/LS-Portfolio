package pl.looksoft.lsportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import pl.looksoft.lsportfolio.base.BaseActivity;

/**
 * Created by Jermey on 2015-08-20.
 */
public class SplashScreenActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 3000;

    Runnable mRunnable;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        };

        mHandler = new Handler();
        mHandler.postDelayed(mRunnable, SPLASH_TIME_OUT);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
