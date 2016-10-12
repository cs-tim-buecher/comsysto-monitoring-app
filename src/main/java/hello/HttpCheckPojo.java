package hello;

public class HttpCheckPojo {

    String name;

    String url;

    String requestMethod;

    Integer expectedStatusCode;

    Integer actualHttpStatusCode;

    Boolean isRedirect = false;

    String redirectToUrl;

    Integer redirectToUrlExpectedStatusCode;

    Integer redirectToUrlActualStatusCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Integer getExpectedStatusCode() {
        return expectedStatusCode;
    }

    public void setExpectedStatusCode(Integer expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    public Integer getActualHttpStatusCode() {
        return actualHttpStatusCode;
    }

    public void setActualHttpStatusCode(Integer actualHttpStatusCode) {
        this.actualHttpStatusCode = actualHttpStatusCode;
    }

    public Boolean getRedirect() {
        return isRedirect;
    }

    public void setRedirect(Boolean redirect) {
        isRedirect = redirect;
    }

    public String getRedirectToUrl() {
        return redirectToUrl;
    }

    public void setRedirectToUrl(String redirectToUrl) {
        this.redirectToUrl = redirectToUrl;
    }

    public Integer getRedirectToUrlExpectedStatusCode() {
        return redirectToUrlExpectedStatusCode;
    }

    public void setRedirectToUrlExpectedStatusCode(Integer redirectToUrlExpectedStatusCode) {
        this.redirectToUrlExpectedStatusCode = redirectToUrlExpectedStatusCode;
    }

    public Integer getRedirectToUrlActualStatusCode() {
        return redirectToUrlActualStatusCode;
    }

    public void setRedirectToUrlActualStatusCode(Integer redirectToUrlActualStatusCode) {
        this.redirectToUrlActualStatusCode = redirectToUrlActualStatusCode;
    }

    public HttpCheckPojo(String name, String url, String requestMethod, Integer expectedStatusCode, String redirectToUrl, Integer redirectToUrlExpectedStatusCode) {
        this.name = name;
        this.url = url;
        this.requestMethod = requestMethod;
        this.expectedStatusCode = expectedStatusCode;
        this.actualHttpStatusCode = -1;
        this.isRedirect = true;
        this.redirectToUrl = redirectToUrl;
        this.redirectToUrlExpectedStatusCode = redirectToUrlExpectedStatusCode;
        this.redirectToUrlActualStatusCode = -1;
    }

    public HttpCheckPojo(String name, String url, String requestMethod, Integer expectedStatusCode) {
        this.name = name;
        this.url = url;
        this.requestMethod = requestMethod;
        this.expectedStatusCode = expectedStatusCode;
        this.actualHttpStatusCode = -1;
        this.isRedirect = false;
    }
}
