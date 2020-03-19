package com.app.myapplication;

import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {
//
    private EditText editText;
    private Button saveButton;
    private TextView title;
    private ImageView logo;
    private TextView settingsTitle;
    private ImageView settingsLogo;
    private TextView addressTitle;
    private TextView bottomText;

    private Animation fromBottom;
    private Animation fromTop;
    private Animation fromSide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        editText=findViewById(R.id.editText);
        saveButton=findViewById(R.id.saveButton);
        title =findViewById(R.id.title);
        logo =findViewById(R.id.logo);
        settingsTitle =findViewById(R.id.settingsTitle);
        settingsLogo =findViewById(R.id.settingsLogo);
        addressTitle =findViewById(R.id.addressTitle);
        bottomText =findViewById(R.id.bottomText);

        fromBottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromTop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromSide= AnimationUtils.loadAnimation(this,R.anim.fromside);
        title.setAnimation(fromTop);
        logo.setAnimation(fromTop);
        settingsTitle.setAnimation(fromTop);
        settingsLogo.setAnimation(fromTop);
        addressTitle.setAnimation(fromTop);
        editText.setAnimation(fromTop);
        saveButton.setAnimation(fromBottom);
        bottomText.setAnimation(fromBottom);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setAddress(String.valueOf(editText.getText()));
                System.out.println(String.valueOf(editText.getText()));
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
