package fr.technocrats.greenitbackend.movies;

import lombok.Data;

import java.util.List;

/**
 * Movies list wrapper
 * TMDB requests send movies list in `results` field of JSON objects
 */
@Data
public class MovieWrapper {
    private List<Movie> results;
}
