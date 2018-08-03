package codesquad.converter;

import org.junit.Test;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateConvertetTest {
    private static final String DATA_FORMAT = "yyyy-MM-dd";
    private LocalDateConverter localDateConverter = new LocalDateConverter(DATA_FORMAT);

    @Test
    public void convert_동작법_확인_테스트1() {
        assertThat(localDateConverter.convert("2018-01-01")).isInstanceOf(LocalDate.class);
    }

    @Test
    public void convert_동작법_확인_테스트2() {
        assertThat(localDateConverter.convert(null)).isNull();
    }
}
