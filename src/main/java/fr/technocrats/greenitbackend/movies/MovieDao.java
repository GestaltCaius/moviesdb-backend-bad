package fr.technocrats.greenitbackend.movies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MovieDao extends CrudRepository<Movie, Integer> {


    @Transactional
    default void addMovieList(List<Movie> movieList, final String imageBaseUrl) {
        movieList.forEach(e -> {
            e.id = null;
            e.backdrop_path = imageBaseUrl + e.backdrop_path;
            e.poster_path = imageBaseUrl + e.poster_path;
            this.save(e);
        });
    }

    List<Movie> findAll();
}
