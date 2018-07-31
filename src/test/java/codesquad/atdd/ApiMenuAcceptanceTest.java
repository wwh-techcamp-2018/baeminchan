package codesquad.atdd;

import codesquad.domain.Menu;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMenuAcceptanceTest extends AcceptanceTest{
    private static final Logger log = LoggerFactory.getLogger(ApiMenuAcceptanceTest.class);

    @Before
    public void setUp(){

    }

    @Test
    public void getMenuAll_성공() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("/api/menu")).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Menu> responseEntity = template().exchange(requestEntity, Menu.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
