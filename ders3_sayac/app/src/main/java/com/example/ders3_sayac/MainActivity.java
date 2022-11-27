package com.example.ders3_sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView value;
    Button minus,plus,ayar;
    int upperLimit;
    int lowerLimit;
    int currentValue;

    boolean upperVib;
    boolean upperSound;
    boolean lowerVib;
    boolean lowerSound;
    SetupClass setupClass;
    Vibrator vibrator=null;
    MediaPlayer player=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent splash=new Intent(this, SplashActivity.class);
        startActivity(splash);
        value=(TextView) findViewById(R.id.value);
        minus=(Button) findViewById(R.id.minus);
        plus=(Button) findViewById(R.id.plus);
        ayar=(Button) findViewById(R.id.ayar);

        Context context=getApplicationContext();
        setupClass=SetupClass.getInstance(context);
        vibrator= (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        player=MediaPlayer.create(context,R.raw.beep);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            valueUpdate(1);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            valueUpdate(-1);
            }
        });
        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i= new Intent(MainActivity.this, SetupActivity.class);
            startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupClass.loadValues();
        getPreferences();
    }
    private void getPreferences() {
        currentValue=setupClass.currentValue;
        upperLimit=setupClass.upperLimit;
        lowerLimit=setupClass.lowerLimit;
        upperVib=setupClass.upperVib;
        upperSound=setupClass.upperSound;
        lowerVib=setupClass.lowerVib;
        lowerSound=setupClass.lowerSound;

    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(action==KeyEvent.ACTION_DOWN)
                    valueUpdate(+5);
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(action==KeyEvent.ACTION_DOWN)
                    valueUpdate(-5);
                return true;
        }
        return super.dispatchKeyEvent(event);
    }
    private void valueUpdate(int step){
        if(step<0){
            if(currentValue + step<lowerLimit){
                currentValue=lowerLimit;
                player.start();
                vibrator.vibrate(100);
            }
            else
                currentValue+=step;
        }
        else{
            if(currentValue + step>upperLimit){
                currentValue=upperLimit;
                player.start();
                vibrator.vibrate(100);
            }
            else
                currentValue+=step;
        }
        value.setText(String.valueOf(currentValue));

    }
}