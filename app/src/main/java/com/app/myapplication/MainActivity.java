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
    public static String address;

    public static void setAddress(String address) {
        MainActivity.address = address;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        address="https://marcinprzybylsk.000webhostapp.com/";
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

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AsyncTaskExample asyncTask=new AsyncTaskExample();
                //            asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");

                HtmlDownloader downloader= (HtmlDownloader) new HtmlDownloader(new HtmlDownloader.AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        if(output == "error")
                        {
//                            status.setText("Błąd połączenia");
                        }
                           else {
//                            status.setText("Poprawne");
                            String toSplit=output;
                            String[] tempArr;
                            tempArr=toSplit.split(":");

                            hr.setText(tempArr[0]);
                            so.setText(tempArr[1]+"%");
                            ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                                                hr,
                                                PropertyValuesHolder.ofFloat("scaleX", 1.05f),
                                                PropertyValuesHolder.ofFloat("scaleY", 1.05f));
                            scaleDown.setDuration(510);

                            scaleDown.setRepeatCount(ObjectAnimator.INFINITE);
                            scaleDown.setRepeatMode(ObjectAnimator.REVERSE);

                            scaleDown.start();

                            ObjectAnimator scaleDown2 = ObjectAnimator.ofPropertyValuesHolder(
                                    so,
                                    PropertyValuesHolder.ofFloat("scaleX", 1.05f),
                                    PropertyValuesHolder.ofFloat("scaleY", 1.05f));
                            scaleDown2.setDuration(510);

                            scaleDown2.setRepeatCount(ObjectAnimator.INFINITE);
                            scaleDown2.setRepeatMode(ObjectAnimator.REVERSE);

                            scaleDown2.start();
                        }
                    }
                },address).execute();
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
