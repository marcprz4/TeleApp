import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HtmlDownloader {
    public HtmlDownloader() {
    }

    //przyklad wywolania:
    //HtmlDownloader downloader=new HtmlDownloader();
    //        downloader.getValue("www.google.com");
    public String getValue(String address) throws IOException {
        String page=download(address);
        String result="";
        int st=page.indexOf("<body>")+6;
        int end=page.indexOf("<div style");
        result=page.substring(st,end);
        String res="";
        for(char c:result.toCharArray()){
            if (c>=48&&c<=58){
                res+=c;
            }
        }
        return res;
    }

    private String download(String address) throws IOException {
        URL url1 = new URL(address);

        // Get the input stream through URL Connection
        URLConnection con = url1.openConnection();
        InputStream is =con.getInputStream();

        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;


        // read each line and write to System.out
//    line=br.
        String result="";
        while ((line = br.readLine()) != null) {
            result+=line+"\n";
    }
        return result;
    }

}
