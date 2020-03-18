package com.app.myapplication;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button button;
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
        text=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AsyncTaskExample asyncTask=new AsyncTaskExample();
                //            asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");

                HtmlDownloader downloader= (HtmlDownloader) new HtmlDownloader(new HtmlDownloader.AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        text.setText(output);
                    }
                },"https://marcinprzybylsk.000webhostapp.com").execute();
            }
        });
    }

}
