package codesquad.atdd;

import codesquad.dto.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Java6Assertions.assertThat;

@Slf4j
public class ApiAdminAcceptanceTest extends AcceptanceTest {
    @Test
    public void 실패_로그인안했을때() {
        CategoryDto category = new CategoryDto();
        RequestEntity<Object> request = RequestEntity.post(URI.create("/admin/api/categories")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(category);
        ResponseEntity<Void> response = template().exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/users/login");
    }

    @Test
    public void 실패_권한없을때() {
        CategoryDto category = new CategoryDto();
        RequestEntity<Object> request = RequestEntity.post(URI.create("/admin/api/categories")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(category);
        ResponseEntity<Void> response = basicAuthTemplate().exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getHeaders().getLocation().getPath()).startsWith("/users/login");
    }

    @Test
    public void 성공_권한있을땐() {
        CategoryDto category = new CategoryDto();
        RequestEntity<Object> request = RequestEntity.post(URI.create("/admin/api/categories")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(category);
        ResponseEntity<Void> response = basicAuthTemplate(adminUser()).exchange(request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
