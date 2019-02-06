package fr.technocrats.greenitbackend.movies;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
@Api(value = "movies", description = "Movies operations")
@CrossOrigin
public class MovieController {

    @Value("${tmdb_base_url}")
    public String tmdbBaseUrl;

    @Value("${tmdb_api_key}")
    public String tmdbApiKey;

    @Value("${tmdb_image_path_base_url}")
    public String tmdbImageBaseUrl;


    @Autowired
    public MovieDao movieDao;

    @RequestMapping(value = "/filldatabase", method = RequestMethod.GET) public @ResponseBody Object fillDatabase() {
        RestTemplate restTemplate = new RestTemplate();

        List<Movie> c = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Object a = restTemplate.getForObject(tmdbBaseUrl + "/movie/popular?api_key=" + tmdbApiKey + "&page="+ i, Object.class);
            LinkedHashMap b = (LinkedHashMap) a;
            List<Movie> tmp = new ArrayList<>();
            for (LinkedHashMap e : (List<LinkedHashMap>) b.get("results")) {
                Movie m = new Movie();
                m.id = (Integer) e.get("id");
                m.title = (String) e.get("title");
                m.overview = (String) e.get("overview");
                m.poster_path = (String) e.get("poster_path");
                m.backdrop_path = (String) e.get("backdrop_path");
                m.release_date = (String) e.get("release_date");
                try {
                    m.vote_average = (Integer) e.get("vote_average");

                } catch (Exception z) {
                    m.vote_average = 42;
                }
                c.add(m);
            }
        }
        movieDao.addMovieList(c, tmdbImageBaseUrl);
        return movieDao.findAll().size();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET) public @ResponseBody Object getMovies() {
        return movieDao.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET) public @ResponseBody Object getMovie(@PathVariable("id") Integer id) {
        List<?> a = movieDao.findAll();
        for (int i = 0; i < a.size(); i++) {
            Object e = a.get(i);
            if (((Movie) e).id == id) {
                return e;
            }
        }
        return null;
    }
}
