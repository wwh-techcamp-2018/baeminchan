package codesquad.web;

import codesquad.dto.JoinUserDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthController {

    private static final String GRANT_TYPE = "authorization_code";
    private static final String API_KEY = "7318f936054d4de4110f910aae224219";
    private static final String REDIR_URI = "http://localhost:8080/oauth/kakaologin";

    public static String getAccessToken(String autorize_code) {
        final String requestUrl = "https://kauth.kakao.com/oauth/token";

        RestTemplate template = new RestTemplate();
        MultiValueMap<String, String> postParams = new LinkedMultiValueMap<>();

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        template.setMessageConverters(converters);

        postParams.add("grant_type", GRANT_TYPE);
        postParams.add("client_id", API_KEY);
        postParams.add("redirect_uri", REDIR_URI);
        postParams.add("code", autorize_code);

        String result = template.postForObject(requestUrl, postParams, String.class);
        log.debug("result : {}", result);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = mapper.readTree(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("access token : {}", jsonNode.get("access_token").toString());
        return jsonNode.get("access_token").toString();
    }

    @GetMapping("kakao-join")
    public String kakaoJoin(@Valid JoinUserDTO joinUserDTO, HttpSession session) {
        return "/";
    }

    @RequestMapping(value = "/kakaologin", produces = "appliation/json", method = {RequestMethod.GET, RequestMethod.POST})
    public String OauthLogin(@RequestParam("code") String code, Model model) {
        log.info("code : {}", code);
        JsonNode userInfo = getUSerInfo(getAccessToken(code));

        model.addAttribute("emailId", userInfo.get("kaccount_email").toString().replaceAll("\"", "").split("@")[0]);
        model.addAttribute("emailDomain", userInfo.get("kaccount_email").toString().replaceAll("\"", "").split("@")[1]);
        model.addAttribute("name", userInfo.get("properties").get("nickname").toString().replaceAll("\"", ""));
        return "/kakao-join";
    }

    private JsonNode getUSerInfo(String accessToken) {
        final String requestUrl = "https://kapi.kakao.com/v1/user/me";

        JsonNode result = null;

        RestTemplate template = new RestTemplate();

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());

        template.setMessageConverters(converters);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_FORM_URLENCODED}));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entityReq = new HttpEntity<String>("", headers);

        ResponseEntity<String> responseEntity = template.exchange(requestUrl, HttpMethod.POST, entityReq, String.class);

        String responseBody = responseEntity.getBody();

        log.debug("body : {}", responseBody);

        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readTree(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
