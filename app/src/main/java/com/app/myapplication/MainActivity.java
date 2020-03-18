package com.app.myapplication;

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
        textView2 =findViewById(R.id.textView2);
        textView3 =findViewById(R.id.textView3);//
        imageView =findViewById(R.id.imageView);

        textView3.setAnimation(fromBottom);
        refreshButton.setAnimation(fromBottom);
        connectionButton.setAnimation(fromTop);
        settingsButton.setAnimation(fromTop);
        textView.setAnimation(fromTop);
        textView2.setAnimation(fromTop);
        imageView.setAnimation(fromTop);


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
                            so.setAnimation(fromSide);
                            hr.setAnimation(fromSide);
                        }
                    }
                },"https://marcinprzybylsk.000webhostapp.com").execute();
            }
        });
    }

}
