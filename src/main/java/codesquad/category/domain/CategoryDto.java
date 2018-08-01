package codesquad.category.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    @NonNull
    private String name;

    private Long parentId;
}
