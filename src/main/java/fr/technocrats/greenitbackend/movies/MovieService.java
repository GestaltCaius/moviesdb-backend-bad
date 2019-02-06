package fr.technocrats.greenitbackend.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Value("${tmdb_base_url}")
    private String tmdbBaseUrl;

    @Value("${tmdb_api_key}")
    private String tmdbApiKey;

    @Value("${tmdb_image_path_base_url}")
    private String tmdbImageBaseUrl;

    @Autowired
    private MovieDao movieDao;

    private RestTemplate restTemplate = new RestTemplate();


    /**
     * Request the first 20 most popular movies from TMDB and then put the movies into the database
     * @return HTTP status code (CREATED or BAD REQUEST)
     */
    public ResponseEntity<String> fillDatabase() {
        List<Movie> movieList = new ArrayList<>();
        final String requestUrl = tmdbBaseUrl + "/movie/popular?api_key=" + tmdbApiKey + "&page=";

        // add first 100 pages of popular movies from TMDB
        for (int i = 1; i <= 20; i++) {
            MovieWrapper movieWrapper = restTemplate.getForObject(
                    requestUrl + i,
                    MovieWrapper.class);
            movieList.addAll(movieWrapper.getResults());
        }

        try {
            movieDao.addMovieList(movieList, tmdbImageBaseUrl);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Find all movies from database
     * @return movies list
     */
    public List<Movie> findAll() { return movieDao.findAll(); }

    /**
     * Get a movie
     * @param id movie's id
     * @return movie which id = `id`
     */
    public Movie findOne(Integer id) {
        return movieDao.findById(id).orElse(null);
    }
}
