package codesquad.dto;

import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryListDto {

    private List<CategoryDto> children;
    private static final Logger log = LoggerFactory.getLogger(CategoryListDto.class); //TODO 나중에 지우기


    public CategoryDto getFirstChild() {
        return this.children.get(0);
    }

//    public CategoryListDto(List<CategoryDto> children) {
//        log.debug(children.get(0).getName());
//        this.children = children;
//    }
}
