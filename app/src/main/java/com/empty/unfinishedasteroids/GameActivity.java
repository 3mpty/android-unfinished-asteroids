package com.empty.unfinishedasteroids;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import java.io.File;
import java.security.Key;


public class GameActivity extends Activity implements View.OnTouchListener {

    private WebView gameView;
    private Button left, right, up, down, shoot, restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setViews();
        setGameView();
    }

    private void setGameView() {
        gameView.getSettings().setJavaScriptEnabled(true);
        gameView.getSettings().setDomStorageEnabled(true);
        gameView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        gameView.getSettings().setAllowFileAccess(true);
        gameView.setWebChromeClient(new WebChromeClient());
        gameView.loadUrl("file:///android_asset/unfinished-asteroids/index.html");
    }

    private void setViews() {
        gameView = (WebView) findViewById(R.id.canvas);
        left = (Button) findViewById(R.id.button_left);
        right = (Button) findViewById(R.id.button_right);
        up = (Button) findViewById(R.id.button_up);
        down = (Button) findViewById(R.id.button_down);
        shoot = (Button) findViewById(R.id.button_shoot);
        restart = (Button) findViewById(R.id.button_restart);

        left.setOnTouchListener(this);
        right.setOnTouchListener(this);
        up.setOnTouchListener(this);
        down.setOnTouchListener(this);
        shoot.setOnTouchListener(this);

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.reload();
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int id = v.getId();

        switch(id) {
            case R.id.button_left:
                return passKeyToWebView(event, KeyEvent.KEYCODE_DPAD_LEFT);
            case R.id.button_right:
                return passKeyToWebView(event, KeyEvent.KEYCODE_DPAD_RIGHT);
            case R.id.button_up:
                return passKeyToWebView(event, KeyEvent.KEYCODE_DPAD_UP);
            case R.id.button_down:
                return passKeyToWebView(event, KeyEvent.KEYCODE_DPAD_DOWN);
            case R.id.button_shoot:
                return passKeyToWebView(event, KeyEvent.KEYCODE_SPACE);
        }
        return false;
    }

    private boolean passKeyToWebView(MotionEvent event, int keycode) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            gameView.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, keycode));
            return true;
        }

        if(event.getAction() == MotionEvent.ACTION_UP) {
            gameView.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, keycode));
            return true;
        }

        return false;
    }
}
