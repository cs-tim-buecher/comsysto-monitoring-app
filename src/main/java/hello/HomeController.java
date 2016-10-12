package hello;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.hibernate.validator.internal.util.privilegedactions.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

    private CloseableHttpClient httpclient = HttpClientBuilder.create()
            .disableRedirectHandling()
            .build();

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
        List<HttpCheckPojo> checks = new ArrayList<>();
        List<HttpCheckPojo> redirectChecks = new ArrayList<>();

        //
        // NORMAL CHECKS
        //
        checks.add(new HttpCheckPojo("comsysto homepage", "https://comsysto.com", "GET", 200));
        checks.add(new HttpCheckPojo("comsysto blog new", "https://comsysto.com/blog", "GET", 200));
        checks.add(new HttpCheckPojo("comsysto test 404", "https://comsysto.com/ffoooooo", "GET", 404));

        //
        // REDIRECT CHECKS
        //
        redirectChecks.add(new HttpCheckPojo("comsysto homepage without ssl", "http://comsysto.com", "GET", 301, "https://comsysto.com", 200));
        redirectChecks.add(new HttpCheckPojo("comsysto blog old url ssl", "https://blog.comsysto.com", "GET", 301, "https://comsysto.com/blog", 200));

        //
        // EXECUTE CHECKS
        //
        for (HttpCheckPojo singleCheck : checks) {
            // FIXME: Currently only GET works
            singleCheck.setActualHttpStatusCode(doGetRequest(singleCheck.getUrl()));
        }
        for (HttpCheckPojo singleCheck : redirectChecks) {
            // FIXME: Currently only GET works
            singleCheck.setActualHttpStatusCode(doGetRequest(singleCheck.getUrl()));
            if (singleCheck.getRedirect()) {
                // FIXME: Actually get Location header from first request and check if URLs match
                singleCheck.setRedirectToUrlActualStatusCode(doGetRequest(singleCheck.getRedirectToUrl()));
            }
        }

        model.addAttribute("checks", checks);
        model.addAttribute("redirectChecks", redirectChecks);
        return "homepage";
    }
    
}
