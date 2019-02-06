package fr.technocrats.greenitbackend.movies;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
@Api(value = "movies", description = "Movies operations")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/filldatabase", method = RequestMethod.GET)
    @ApiOperation(value = "Fill database with movies from TheMovieDB API")
    public @ResponseBody
    ResponseEntity<String> fillDatabase() {
        return movieService.fillDatabase();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Get all movies")
    public @ResponseBody
    List<Movie> getMovies() {
        return movieService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get a movie by its ID")
    public @ResponseBody
    Movie getMovie(
            @ApiParam(name = "id", value = "Movie's ID")
            @PathVariable("id") Integer id
    ) {
        return movieService.findOne(id);
    }
}
