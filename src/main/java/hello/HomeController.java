package hello;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class HomeController {

    private CloseableHttpClient httpclient = HttpClients.createDefault();

    private Integer doGetRequest(String url) {
        Integer statusCode = -1;
        try {
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);
            statusCode = response.getStatusLine().getStatusCode();
            response.close();
        } catch (Exception e) {
            System.err.println("Fatal protocol violation: " + e.getMessage());
            e.printStackTrace();
            return -2;
        }
        return statusCode;
    }

    @RequestMapping("/")
    public String index(Model model) {
        HashMap<String, Integer> urlsAndStatusCodes = new HashMap<>();

        urlsAndStatusCodes.put("comsysto.com HTTP", doGetRequest("http://comsysto.com"));
        urlsAndStatusCodes.put("comsysto.com HTTPS", doGetRequest("https://comsysto.com"));
        urlsAndStatusCodes.put("blog.comsysto.com = old", doGetRequest("https://blog.comsysto.com"));
        model.addAttribute("urlsAndStatusCodes", urlsAndStatusCodes);
        return "homepage";
    }
    
}
