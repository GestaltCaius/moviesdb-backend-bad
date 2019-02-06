package fr.technocrats.greenitbackend.movies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieDao extends CrudRepository<Movie, Integer> {


    @Transactional
    default void addMovieList(List<Movie> movieList, final String imageBaseUrl) {
        movieList.forEach(movie -> {
            // force id to null, so that Hibernate inserts a new element
            movie.setId(null);
            // add tmdb base url to path
            // path provided by TMDB API do not include the whole URL so we have to add it ourselves
            movie.setBackdrop_path(String.format("%s%s", imageBaseUrl, movie.getBackdrop_path()));
            movie.setPoster_path(String.format("%s%s", imageBaseUrl, movie.getPoster_path()));
            this.save(movie);
        });
    }

    @Transactional
    List<Movie> findAll();

    @Transactional
    Optional<Movie> findById(Integer id);
}
