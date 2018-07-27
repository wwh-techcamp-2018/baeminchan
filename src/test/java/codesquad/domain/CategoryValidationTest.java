package codesquad.domain;

import codesquad.validation.ValidationUtil;
import org.junit.Test;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CategoryValidationTest {


    @Test
    public void titleBlankInValidate(){
        Category category = new Category();
        ValidationUtil.assertValidate(category, Arrays.asList(
                NotBlank.class,
                Size.class
        ));
    }

    @Test
    public void titleSizeInvalidate(){
        Category category = new Category();
        category.setTitle("가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사가나다라마바사");
        ValidationUtil.assertValidate(category, Arrays.asList(
                Size.class
        ));
    }
}