package codesquad.atdd;

import codesquad.domain.Menu;
import codesquad.domain.User;
import codesquad.repository.MenuRepository;
import codesquad.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiMenuAcceptanceTest extends AcceptanceTest{
    private static final Logger log = LoggerFactory.getLogger(ApiMenuAcceptanceTest.class);

    @Resource(name = "passwordEncoder")
    PasswordEncoder encoder;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    UserRepository userRepository;

    User defaultAdminUser;

    @Before
    public void setUp(){
        User testAdmin = new User("testAdmin@admin.com","test1234","관리맨","010-4333-4442");
        testAdmin.registAdmin();
        testAdmin.encrypt(encoder);
        defaultAdminUser = userRepository.save(testAdmin);
    }

    @After
    public void setDown(){
        userRepository.delete(defaultAdminUser);
    }

    @Test
    public void getMenuAll_성공() throws URISyntaxException {
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("/api/menu/")).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Menu> responseEntity = template().exchange(requestEntity, Menu.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getParent()).isNull();
    }

    @Test
    public void get단일메뉴_성공() throws URISyntaxException {
        Menu menu = menuRepository.findByParentIsNull().get();
        RequestEntity<Void> requestEntity = RequestEntity.get(new URI("/api/menu/"+menu.getId())).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Menu> responseEntity = template().exchange(requestEntity, Menu.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isEqualTo(menu.getId());
    }

    @Test
    public void delete단일메뉴() throws URISyntaxException{
        Menu menu = menuRepository.save(new Menu("test", null, null, 0, 0));
        RequestEntity<Void> requestEntity = RequestEntity.delete(new URI("/api/admin/menu/"+menu.getId())).accept(MediaType.APPLICATION_JSON).build();
        ResponseEntity<Void> responseEntity = basicAuthTemplate(defaultAdminUser).exchange(requestEntity, Void.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void create메뉴() throws URISyntaxException{
        Menu menu = menuRepository.findByParentIsNull().get();
        String body ="{\"parentId\":"+menu.getId()+",\n\"childMenuName\":"+"\"testMenu\"}";
        RequestEntity<String> requestEntity = RequestEntity.post(new URI("/api/admin/menu")).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).body(body,String.class);
        ResponseEntity<Void> responseEntity = basicAuthTemplate(defaultAdminUser).exchange(requestEntity, Void.class);
        log.debug("response : {}",responseEntity);
    }
}
