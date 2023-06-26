package com.dh.movie.service;


import com.dh.movie.event.SaveMovieEventProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {


    private final MovieRepository movieRepository;

    private final SaveMovieEventProducer saveMovieEventProducer;

    public MovieService(MovieRepository movieRepository, SaveMovieEventProducer saveMovieEventProducer) {
        this.movieRepository = movieRepository;
        this.saveMovieEventProducer = saveMovieEventProducer;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        saveMovieEventProducer.publishFinalizarCursoEvent(movie);
        return movieRepository.save(movie);
    }

    public List<Movie> findAll(){
        return movieRepository.findAll();
    }
}
