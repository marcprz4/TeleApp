package com.example.tele;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import android.widget.Button;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView connection = (TextView) findViewById(R.id.status);
        connection.setText("Niepoprawne");

        //przycisk
        Button button1 = findViewById(R.id.connect);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewResult = findViewById(R.id.text_view_result);

                OkHttpClient client = new OkHttpClient();
                String url = "https://reqres.in/api/users?page=2";//strona z której pobieramy json

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful())
                        {
                            connection.setText("Poprawne");
                            final String myResponse = response.body().string();

                            MainActivity.this.runOnUiThread((new Runnable() {
                                @Override
                                public void run() {
                                    mTextViewResult.setText(myResponse);
                                }
                            }));

                        }
                    }
                });
            }
        });
        {

        }


    }
}
