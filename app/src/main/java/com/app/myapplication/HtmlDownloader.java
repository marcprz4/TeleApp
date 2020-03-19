package com.app.myapplication;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HtmlDownloader extends AsyncTask<Void, Void, String>  {
    public interface AsyncResponse {
        void processFinish(String output);
    }
    public AsyncResponse delegate = null;

    private String address;
    private String value;

    public HtmlDownloader(AsyncResponse delegate, String address) {
        this.delegate = delegate;
        this.address=address;
    }

    public String getValue() {
        return value;
    }

    //przyklad wywolania:
    //HtmlDownloader downloader=new HtmlDownloader();
    //        downloader.getValue("www.google.com");
    private String loadValue() throws IOException {
        String page=download();
        String result="";
        int st=page.indexOf("<body>")+6;
        int end=page.indexOf("</body");
        result=page.substring(st,end);
        String res="";
        for(char c:result.toCharArray()){
            if (c>=48&&c<=58){
                res+=c;
            }
        }
        return res;
    }

    private String download() throws IOException {
        URL url1 = new URL(address);
        URLConnection con = url1.openConnection();
        InputStream is =con.getInputStream();


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        String result="";
        while ((line = br.readLine()) != null) {
            result+=line+"\n";
        }
        return result;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            String ret="";
            do {
                Thread.sleep(1000);
                ret=loadValue();
            } while(ret.length()<=1);
            return ret;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "EE:EE";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
