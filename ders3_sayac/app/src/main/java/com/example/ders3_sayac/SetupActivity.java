package com.example.ders3_sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SetupActivity extends AppCompatActivity {
    int upperLimit,lowerLimit;
    Button uplus,uminus,dplus,dminus;
    EditText upValue,dValue;
    CheckBox upVib,upSound,lowVib,lowSound;

    SetupClass setupClass;

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        uplus=(Button) findViewById(R.id.up_plus);
        uminus=(Button) findViewById(R.id.up_minus);
        upValue=(EditText) findViewById(R.id.upper_Limit);
        dplus=(Button) findViewById(R.id.low_plus);
        dminus=(Button) findViewById(R.id.low_minus);
        dValue=(EditText) findViewById(R.id.lowLimit);

        upVib=(CheckBox) findViewById(R.id.up_vib);
        upSound=(CheckBox) findViewById(R.id.up_sound);
        lowVib=(CheckBox) findViewById(R.id.low_vib);
        lowSound=(CheckBox) findViewById(R.id.low_sound);



        context=getApplicationContext();
        sharedPreferences= context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        setupClass=SetupClass.getInstance(context);

        upValue.setText(String.valueOf(upperLimit));
        dValue.setText(String.valueOf(lowerLimit));

        uplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upperLimit++;
                upValue.setText(String.valueOf(upperLimit));
            }
        });
        uminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upperLimit--;
                upValue.setText(String.valueOf(upperLimit));
            }
        });
        upValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                upperLimit=Integer.valueOf(upValue.getText().toString());
            }
        });
        dplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowerLimit++;
                dValue.setText(String.valueOf(lowerLimit));
            }
        });
        dminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lowerLimit--;
                dValue.setText(String.valueOf(lowerLimit));
            }
        });
        dValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                lowerLimit=Integer.valueOf(dValue.getText().toString());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Context context=getApplicationContext();
        sharedPreferences = context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        SetupClass setupClass=SetupClass.getInstance(context);
        setupClass.setValues(upperLimit, lowerLimit,upVib.isChecked(),
                upSound.isChecked(),lowVib.isChecked(),lowSound.isChecked());
        editor.putInt("UpperLimit",upperLimit);
        lowerLimit=Integer.valueOf(dValue.getText().toString());
        editor.putInt("LowerLimit",lowerLimit);
        editor.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        setupClass.loadValues();
        loadValues();
        dValue.setText(String.valueOf(lowerLimit));
        upValue.setText(String.valueOf(upperLimit));}

        public void loadValues(){
        upperLimit=setupClass.upperLimit;
        lowerLimit=setupClass.lowerLimit;
    }
}