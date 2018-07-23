package codesquad.support;

public class JsonResponse { //body - json

    private String url;

    private String message;

    public JsonResponse(){}
    public JsonResponse(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
