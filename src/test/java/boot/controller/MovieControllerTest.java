package boot.controller;

import boot.model.ApiMovie;
import boot.service.ApiService;
import boot.service.MovieService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

//@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieController.class, secure = false)
public class MovieControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApiService apiService;

    @MockBean
    private MovieService movieService;

//    @Before
//    public void setUp() {
//        apiService = Mockito.mock(ApiService.class);
//
//
//    }

    ApiMovie sda = new ApiMovie(10, 1, false,
            "10.0", "TestMovie", 3.00, "poster_path",
            "OriginalLanguange", "OriginalTitle", new Integer[]{12},
            "backdrop_path", false, "overview", "releaseDate");

    ApiMovie mockMovie = new ApiMovie(94, 229407, false, "7.7",
            "Minions: Puppy", 1.646499, "/maBSw4YgUzjQoowiMI51FG0Npzc.jpg",
            "en", "Minions: Puppy", new Integer[]{16, 10751},
            "/eAKzsYVHiC0bSNudVo9IgREurmm.jpg", false, "A Minion, seeing many owners walk their dogs, wants a puppy of his own." +
            " He tries to leash a ladybug but fails." +
            " Luckily, a UFO that sweeps away the ladybug somehow agrees to become a Puppy.", "2013-12-10");

    List<ApiMovie> movies = Arrays.asList(mockMovie);

    String exampleMoviesJson = "[\n" +
            "    {\n" +
            "        \"vote_count\": 94,\n" +
            "        \"id\": 229407,\n" +
            "        \"video\": false,\n" +
            "        \"vote_average\": \"7.7\",\n" +
            "        \"title\": \"Minions: Puppy\",\n" +
            "        \"popularity\": 1.646499,\n" +
            "        \"poster_path\": \"/maBSw4YgUzjQoowiMI51FG0Npzc.jpg\",\n" +
            "        \"original_language\": \"en\",\n" +
            "        \"original_title\": \"Minions: Puppy\",\n" +
            "        \"genre_ids\": [\n" +
            "            16,\n" +
            "            10751\n" +
            "        ],\n" +
            "        \"backdrop_path\": \"/eAKzsYVHiC0bSNudVo9IgREurmm.jpg\",\n" +
            "        \"adult\": false,\n" +
            "        \"overview\": \"A Minion, seeing many owners walk their dogs, wants a puppy of his own. He tries to leash a ladybug but fails. Luckily, a UFO that sweeps away the ladybug somehow agrees to become a Puppy.\",\n" +
            "        \"release_date\": \"2013-12-10\"\n" +
            "    }\n" +
            "]";


    @Test
    public void retrieveMovies() throws Exception {
        Mockito.when(
                apiService.findByTitleList(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(movies);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/search/api/byTitleList?t=Minion:+Puppy&p=1").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        String expected = "{\"vote_count\":94,\"id\":229407,\"video\":false,\"vote_average\":\"7.7\"" +
                ",\"title\":\"Minions: Puppy\",\"popularity\":1.646499,\"poster_path\":\"/maBSw4YgUzjQoowiMI51FG0Npzc.jpg\"" +
                ",\"original_language\":\"en\",\"original_title\":\"Minions: Puppy\",\"genre_ids\":[16,10751]" +
                ",\"backdrop_path\":\"/eAKzsYVHiC0bSNudVo9IgREurmm.jpg\",\"adult\":false" +
                ",\"overview\":\"A Minion, seeing many owners walk their dogs, wants a puppy of his own. He tries to leash a ladybug but fails." +
                " Luckily, a UFO that sweeps away the ladybug somehow agrees to become a Puppy.\",\"release_date\":\"2013-12-10\"}";

        JSONObject jsonObject = new JSONObject(expected);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        System.out.println(
                jsonArray.toString());

        JSONAssert.assertEquals(jsonArray.toString(), result.getResponse().getContentAsString(), false);
//        JSONAssert.assertEquals(expected, result.getResponse()
//                .getContentAsString(), false);
    }
}
