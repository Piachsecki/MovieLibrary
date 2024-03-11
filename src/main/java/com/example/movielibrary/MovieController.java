package com.example.movielibrary;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/test")
    public int test() {
        return 1;
    }

    @GetMapping("/get_all")
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @GetMapping("/get_all/{id}")
    public Movie getById(@PathVariable("id") int id) {
        return movieRepository.getById(id);
    }

    @PostMapping("/add")
    public int add(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    @PutMapping("/update/{id}")
    public int add(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movieFound = movieRepository.getById(id);
        if (movieFound != null) {
            movieFound.setMovie_name(updatedMovie.getMovie_name());
            movieFound.setRating(updatedMovie.getRating());
            movieRepository.update(movieFound);
            return 1;
        } else {
            return -1;
        }
    }


    @PatchMapping("/update/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movieFound = movieRepository.getById(id);
        if (movieFound != null) {
            if (updatedMovie.getMovie_name() != null)
                movieFound.setMovie_name(updatedMovie.getMovie_name());
            if (updatedMovie.getRating() > 0)
                movieFound.setRating(updatedMovie.getRating());
            movieRepository.update(movieFound);
            return 1;
        } else {
            return -1;
        }
    }

}
