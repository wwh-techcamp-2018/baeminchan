package codesquad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class RestResponse<T> {

    @NonNull
    private T data;

}
