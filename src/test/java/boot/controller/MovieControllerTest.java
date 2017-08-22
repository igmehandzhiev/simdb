package boot.controller;

import boot.model.ApiMovie;
import boot.model.Genres;
import boot.model.GenresRequest;
import boot.service.ApiService;
import boot.service.MovieService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
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
import java.util.concurrent.CompletableFuture;

/**
 * MovieController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Aug 22, 2017</pre>
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = MovieController.class, secure = false)
public class MovieControllerTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

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

    ApiMovie sda = new ApiMovie(101, 2, true,
            "", "M", 1.01, "pOsTeR_PATH",
            "bg", "", new Integer[]{1, 2, 3},
            "", true, "OvErViEw.", "2000-12-12");

    ApiMovie mockMovie = new ApiMovie(100, 1, false, "1.1",
            "Title", 1.11, "/poster_path.jpg",
            "ol", "Original_Title", new Integer[]{1, 2},
            "/backdrop_path.jpg", false, "Overview.", "year-month-date");

    List<ApiMovie> movies = Arrays.asList(mockMovie);

    String exampleMoviesJson = "[\n" +
            "    {\n" +
            "        \"vote_count\": 100,\n" +
            "        \"id\": 1,\n" +
            "        \"video\": false,\n" +
            "        \"vote_average\": \"1.1\",\n" +
            "        \"title\": \"Title\",\n" +
            "        \"popularity\": 1.11,\n" +
            "        \"poster_path\": \"/poster_path.jpg\",\n" +
            "        \"original_language\": \"ol\",\n" +
            "        \"original_title\": \"Original_Title\",\n" +
            "        \"genre_ids\": [\n" +
            "            1,\n" +
            "            2\n" +
            "        ],\n" +
            "        \"backdrop_path\": \"/backdrop_path.jpg\",\n" +
            "        \"adult\": false,\n" +
            "        \"overview\": \"Overview.\",\n" +
            "        \"release_date\": \"year-month-date\"\n" +
            "    }\n" +
            "]";

    /**
     * Method: findApiByTitleList(@RequestParam("t") String title, @RequestParam("p") int page)
     */
    @Test
    public void testFindApiByTitleList() throws Exception {
        Mockito.when(
                apiService.findByTitleList(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(movies);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/movie/search/api/byTitleList?t=blabla&p=42").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"vote_count\":100,\"id\":1,\"video\":false,\"vote_average\":\"1.1\"" +
                ",\"title\":\"Title\",\"popularity\":1.11,\"poster_path\":\"/poster_path.jpg\"" +
                ",\"original_language\":\"ol\",\"original_title\":\"Original_Title\",\"genre_ids\":[1,2]" +
                ",\"backdrop_path\":\"/backdrop_path.jpg\",\"adult\":false" +
                ",\"overview\":\"Overview.\",\"release_date\":\"year-month-date\"}";

        JSONObject jsonObject = new JSONObject(expected);
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        JSONAssert.assertEquals(jsonArray.toString(), result.getResponse().getContentAsString(), false);
    }

    /**
     * Method: getGenres()
     */
    @Test
    public void testGetGenres() throws Exception {
        Genres genre1 = new Genres(1, "Genre", true);
        Genres genre2 = new Genres(2, "Genre2", false);
        GenresRequest genresRequest = new GenresRequest();
        genresRequest.setGenres(Arrays.asList(genre1, genre2));
        System.out.println("GENRES " + genresRequest.getGenres());

        Mockito.when(apiService.getGenres()).thenReturn(CompletableFuture.completedFuture(genresRequest.getGenres()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/movie/search/api/genres");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println("Async result: " + result.getAsyncResult());

        String expected = "[{'id': 1, 'name': 'Genre', 'show': true}," +
                "{'id': 2, 'name': 'Genre2', 'show': false}]";

        JSONArray jsonArray = new JSONArray(expected);
        JSONAssert.assertEquals(jsonArray.toString(), result.getAsyncResult().toString(), false);


    }

    /**
     * Method: findApiAll()
     */
    @Test
    public void testFindApiAll() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findApiByTitle(@RequestParam("t") String title, @RequestParam("p") int page)
     */
    @Test
    public void testFindApiByTitle() throws Exception {

    }

    /**
     * Method: pagesByTitle(@RequestParam("t") String title)
     */
    @Test
    public void testPagesByTitle() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: discoverMovies(@RequestParam("y") String year, @RequestParam("g") String genres, @RequestParam("r") String rating, @RequestParam("p") int page)
     */
    @Test
    public void testDiscoverMovies() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: pagesDiscovery(@RequestParam("y") String year, @RequestParam("g") String genres, @RequestParam("r") String rating)
     */
    @Test
    public void testPagesDiscovery() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findAll()
     */
    @Test
    public void testFindAll() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByTitle(@RequestParam("t") String title)
     */
    @Test
    public void testFindByTitle() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByGenres(@RequestParam("g") String[] genres)
     */
    @Test
    public void testFindByGenres() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByTitleYearRating(@RequestParam("t") String title, @RequestParam("y") String year, @RequestParam("r") Double rating)
     */
    @Test
    public void testFindByTitleYearRating() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: findByTitleYearGenreRating(@RequestParam("t") String title, @RequestParam("y") String year, @RequestParam("g") String[] genres, @RequestParam("r") Double rating)
     */
    @Test
    public void testFindByTitleYearGenreRating() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: handleTodoNotFound(MovieNotFoundException ex)
     */
    @Test
    public void testHandleTodoNotFound() throws Exception {
//TODO: Test goes here... 
    }

    @Test
    public void retrieveMovies() throws Exception {

//        JSONAssert.assertEquals(expected, result.getResponse()
//                .getContentAsString(), false);
    }


} 
