package codesquad.utils;

import lombok.*;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public class RestResponse<T> {
    @NonNull
    private T data;
}
