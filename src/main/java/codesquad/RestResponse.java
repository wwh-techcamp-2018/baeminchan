package codesquad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class RestResponse<T> {

    @NonNull
    private T data;

    public RestResponse(T data) {
        this.data = data;
    }
}
