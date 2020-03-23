package com.app.myapplication;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView hr, so;//, status;
    private Button refreshButton;
    private Button connectionButton;
    private Button settingsButton;
    private Animation fromBottom;
    private Animation fromTop;
    private Animation fromSide;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private ImageView imageView;
    public static String address="http://192.168.8.111/";
    int hr_average;

    int so_average;

    int i=0;
    public static void setAddress(String address) {
        MainActivity.address = address;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        setContentView(R.layout.activity_main);
        fromBottom= AnimationUtils.loadAnimation(this,R.anim.frombottom);
        fromTop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
        fromSide= AnimationUtils.loadAnimation(this,R.anim.fromside);
        hr =findViewById(R.id.hr);
        so =findViewById(R.id.so);//
//        status=findViewById(R.id.status);
        refreshButton =findViewById(R.id.refreshButton);//
        connectionButton =findViewById(R.id.connectionButton);
        settingsButton =findViewById(R.id.settingsButton);

        textView =findViewById(R.id.textView);
        textView2 =findViewById(R.id.textView);
        textView3 =findViewById(R.id.textView3);//
        imageView =findViewById(R.id.imageView);

        textView3.setAnimation(fromBottom);
        refreshButton.setAnimation(fromBottom);
        connectionButton.setAnimation(fromTop);
        settingsButton.setAnimation(fromTop);
        textView.setAnimation(fromTop);
        textView2.setAnimation(fromTop);
        imageView.setAnimation(fromTop);
        so.setAnimation(fromSide);
        hr.setAnimation(fromSide);
        hr.setText("--");
        so.setText("--");
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                while(i<20){
                    HtmlDownloader downloader= (HtmlDownloader) new HtmlDownloader(new HtmlDownloader.AsyncResponse() {
                        @Override
                        public void processFinish(String output) {
                            if(output == "error")
                            {
                                System.err.println("ERROR");
                            }
                            else {
                                String toSplit=output;
                                String[] tempArr;
                                tempArr=toSplit.split(":");
                                hr.setText(tempArr[0]);
                                so.setText(tempArr[1]);
                                hr_average+=Integer.parseInt(tempArr[0]);
                                so_average+=Integer.parseInt(tempArr[1]);

                                if(i==10 || i==20) {
                                    ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                                            hr_average / 10,
                                            PropertyValuesHolder.ofFloat("scaleX", 1.08f),
                                            PropertyValuesHolder.ofFloat("scaleY", 1.08f));
                                    scaleDown.setDuration(450);

                                    scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
                                    scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

                                    scaleDown.start();

                                    ObjectAnimator scaleDown2 = ObjectAnimator.ofPropertyValuesHolder(
                                            so_average / 10,
                                            PropertyValuesHolder.ofFloat("scaleX", 1.08f),
                                            PropertyValuesHolder.ofFloat("scaleY", 1.08f));
                                    scaleDown2.setDuration(450);

                                    scaleDown2.setRepeatCount(ObjectAnimator.INFINITE);
                                    scaleDown2.setRepeatMode(ObjectAnimator.REVERSE);

                                    scaleDown2.start();
                                    hr_average=0;
                                    so_average=0;
                                }
                            }
                        }
                    },address).execute();
                    i++;

                }
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}
