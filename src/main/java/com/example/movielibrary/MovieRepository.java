package com.example.movielibrary;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Movie> getAll(){
        return jdbcTemplate
                .query(
                        "SELECT * FROM movie",
                        BeanPropertyRowMapper
                                .newInstance(Movie.class)
                );
    }

    public Movie getById(@NonNull int id){
        return jdbcTemplate
                .queryForObject(
                        "SELECT * FROM movie WHERE movie.id = ?",
                        BeanPropertyRowMapper
                                .newInstance(Movie.class),
                        id
                );
    }

    public int save(List<Movie> movies) {

        for (Movie movie : movies) {
            jdbcTemplate.update(
                    "INSERT INTO movie(movie_name, rating) VALUES (?, ?)",
            movie.getMovie_name(),
            movie.getRating()
        );
        }


        return 1;
    }

    public int update(Movie movie) {


            jdbcTemplate.update(
                    "UPDATE movie SET movie_name = ?, rating = ? WHERE id = ?",
                    movie.getMovie_name(),
                    movie.getRating(),
                    movie.getId()
            );



        return 1;
    }

}
