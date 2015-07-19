package webscrapper;

import java.net.MalformedURLException;
import java.net.URL;

public class WebScrapper  {

    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:2121/login");
        Request req = new Request(url);
        req.setCookiesHeader(req.getCookie());
        req.header();
        req.setDoOutput(true);
        req.write("username=samundra&password=music3");
        System.out.println("Response Header"+req.getAllHeaders());
        System.out.println("Response: "+req.response());
        
    }

}
