package webscrapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samundra kc <samundrak@yahoo.com>
 * Email: Samundrak@yahoo.com
 */
public class Request {

    URL url;
    URLConnection uc;

    public Request(URL url) {
        try {
            this.url = url;
            this.uc = this.url.openConnection();
        } catch (IOException ex) {
            //Ex
        }
    }

    void setURLConnection(){
        try {
            this.uc = this.url.openConnection();
            uc.setDoOutput(true);
        } catch (IOException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void setDoOutput(boolean bool){
        uc.setDoOutput(bool);
    }
    
    public void setCookiesHeader(String cookies){
        uc.setRequestProperty("Cookie", cookies);
    }
    public void header() {
        uc.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        uc.setRequestProperty("Accept-Encoding", "gzip, deflate");
        uc.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
        uc.setRequestProperty("Cache-Control", "max-age=0");
        uc.setRequestProperty("Connection", "keep-alive");
        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.81 Safari/537.36");
        uc.setRequestProperty("Host", "beingprogrammer.com");
//        uc.setRequestProperty("Origin", "http://beingprogrammer.com");
//        uc.setRequestProperty("Referer", "http://beingprogrammer.com/spritesheet-maker/");
        
    }

    public void write(String values) {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(uc.getOutputStream());
            //"mig33-username=winklegul&mig33-password=roseflower1"
            osw.write(values);
            osw.close();
        } catch (IOException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                osw.close();
            } catch (IOException ex) {
                Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String getLocation(){
        if(uc.getHeaderField("Location").isEmpty()){
            return uc.getHeaderField("Location");
        }else{
            return null;
        }
    }
    
    public Map<String , List<String>> getAllHeaders(){
        return uc.getHeaderFields();
    }
    
    public String response(){
        String in ="";
        try {
            BufferedReader  br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String input;
            while((input = br.readLine() )!= null){
                in = in + input ;
            }
        } catch (IOException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
        return in;
    }
    
    public void setCookie(String cookie){
        try {
            PrintWriter pw = new PrintWriter("cookies.txt","UTF-8");
            pw.println(cookie);
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getHeader(String key){
        return uc.getHeaderField(key);
    }
    public String getCookie(){
        String cookie = "";
        if(new File("cookies.txt").exists()){
        try (BufferedReader br = new BufferedReader(new FileReader("cookies.txt")))
		{
 
			String sCurrentLine;
 
			while ((sCurrentLine = br.readLine()) != null) {
				 cookie =  cookie +sCurrentLine;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }else{
            
   }
        return cookie;
}

    void setHeader(String key, String val) {
         uc.setRequestProperty(key, val);//ange body of generated methods, choose Tools | Templates.
    }
}
