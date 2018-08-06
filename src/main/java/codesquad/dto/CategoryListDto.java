package codesquad.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDto {

    private List<CategoryDto> children;
    private static final Logger log = LoggerFactory.getLogger(CategoryListDto.class);

    @JsonIgnore
    public CategoryDto getFirstChild() {
        if (this.children.isEmpty()) {
            return null;
        }
        return this.children.get(0);
    }
}
