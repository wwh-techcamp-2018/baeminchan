package codesquad.support;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    List<String> error;

    public ErrorResponse(){
        error = new ArrayList<>();
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public void registErrorMessage(String message){
        error.add(message);
    }
}
