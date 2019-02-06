package fr.technocrats.greenitbackend.movies;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "MOVIES")
@Data
public class Movie {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    @Column
    public String title;

    @Column
    public String poster_path;

    @Column
    public String backdrop_path;

    @Column(length = 1024)
    public String overview;

    @Column
    public String release_date;

    @Column
    public Integer vote_average;

}
