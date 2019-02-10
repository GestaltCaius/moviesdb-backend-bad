package fr.technocrats.greenitbackend.movies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MovieDao extends CrudRepository<Movie, Integer> {


    @Transactional
    default void addMovieList(List<Movie> movieList) {
        movieList.forEach(e -> {
            e.id = null;
            e.backdrop_path = "https://image.tmdb.org/t/p/original" + e.backdrop_path;
            e.poster_path = "https://image.tmdb.org/t/p/original" + e.poster_path;
            this.save(e);
        });
    }

    List<Movie> findAll();
}
