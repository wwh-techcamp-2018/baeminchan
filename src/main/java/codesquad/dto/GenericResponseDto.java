package codesquad.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseDto<T> {
    private int status;
    private String message;
    private T body;

    public GenericResponseDto(T body) {
        this(HttpStatus.OK.value(), null, body);
    }
}
