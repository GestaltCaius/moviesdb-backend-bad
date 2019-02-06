package fr.technocrats.greenitbackend.movies;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "MOVIES")
@Data
public class Movie {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "Unique ID")
    private Integer id;

    @Column
    @ApiModelProperty(value = "English title")
    private String title;

    @Column
    @ApiModelProperty(value = "Movie poster")
    private String poster_path;

    @Column
    @ApiModelProperty(value = "Background image for the movie")
    private String backdrop_path;

    @Column(length = 1024)
    @ApiModelProperty(value = "Synopsis")
    private String overview;

    @Column
    @ApiModelProperty(value = "Release date")
    private String release_date;

    @Column
    @ApiModelProperty(value = "Rating (0 to 10)")
    private Integer vote_average;

}
