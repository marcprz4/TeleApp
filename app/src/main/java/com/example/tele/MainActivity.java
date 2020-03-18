package com.example.tele;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import android.widget.Button;




public class MainActivity extends AppCompatActivity {
    public String Rate;


    private TextView mTextViewResult;
    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView connection = (TextView) findViewById(R.id.status);
        connection.setText("Nieudane");

        //przycisk
        Button button1 = findViewById(R.id.connect);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewResult = findViewById(R.id.text_view_result);

                HtmlDownloader downloader=new HtmlDownloader();
                try {
                    downloader.getValue("https://marcinprzybylsk.000webhostapp.com/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Rate=downloader.toString();

                connection.setText("Udane");

                MainActivity.this.runOnUiThread((new Runnable() {
                                @Override
                                public void run() {
                                    mTextViewResult.setText(Rate); }
                            }));


        }

        });
    }
}








