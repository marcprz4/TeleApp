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

    private TextView text_hr, text_ox, status;
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
        text_hr=findViewById(R.id.text_view_result);
        text_ox=findViewById(R.id.text_view_result_ox);
        status=findViewById(R.id.status);
        button=findViewById(R.id.connect);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AsyncTaskExample asyncTask=new AsyncTaskExample();
                //            asyncTask.execute("https://www.tutorialspoint.com/images/tp-logo-diamond.png");

                HtmlDownloader downloader= (HtmlDownloader) new HtmlDownloader(new HtmlDownloader.AsyncResponse() {
                    @Override
                    public void processFinish(String output) {
                        if(output == "error")
                        {
                            status.setText("Niepoprawne");
                        }
                           else {
                            status.setText("Poprawne");
                            final String heart = output.substring(0, 3);
                            final String oxy = output.substring(4, 6);
                            text_hr.setText(heart);
                            text_ox.setText(oxy);
                        }
                    }
                },"https://marcinprzybylsk.000webhostapp.com").execute();
            }
        });
    }

}
