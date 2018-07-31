package codesquad.domain;

import codesquad.validation.ValidationUtil;
import org.junit.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Arrays;

public class CategoryTest {

    private Category category;

    @Test
    public void emptyTitle() {
        category = new Category();
        ValidationUtil.assertValidate(category, Arrays.asList(
            NotBlank.class,
                Size.class
        ));
    }

    @Test
    public void tooLongTitle() {
        category = new Category("qwertyuiopasdfghjkl;zxcvbnm,wertyuiopdfghjk");
        ValidationUtil.assertValidate(category, Arrays.asList(
                Size.class
        ));
    }
}
