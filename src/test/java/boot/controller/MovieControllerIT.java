package boot.controller;

import boot.Application;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testPagesDiscovery() throws JSONException, UnsupportedEncodingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        UriTemplate uriTemplate = new UriTemplate("/movie/search/api/discover/pages{y}&{g}&{r}");
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                createUrlWithPort("/movie/search/api/discover/pages?y=2012&g=12&r=5"),
                HttpMethod.GET, entity, String.class);

        String expected = "7";

        JSONAssert.assertEquals(expected, responseEntity.getBody(), false);

    }

    private String createUrlWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
