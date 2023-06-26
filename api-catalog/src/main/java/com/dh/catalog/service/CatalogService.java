package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;

    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;


    @Autowired
    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, MovieRepository movieRepository, SerieRepository serieRepository){
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
    }
    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "getCatalogMovieFallBack")
    public List<Movie> getMovieByGenre(String genre){
        return movieServiceClient.getMovieByGenre(genre);
    }
    public List<Movie> getCatalogMovieFallBack(String genre, Throwable t) throws Exception {
        try {
            return movieRepository.findAllByGenre(genre);
        } catch (Exception e){
            throw new Exception("Could not delete customer");
        }
    }

    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "getCatalogSerieFallBack")
    public List<Serie> getSerieByGenre(String genre){
        return serieServiceClient.getSerieByGenre(genre);
    }

    public List<Serie> getCatalogSerieFallBack(String genre, Throwable t) throws Exception {
        try {
            return serieRepository.findAllByGenre(genre);
        } catch (Exception e){
            throw new Exception("Could not delete customer");
        }
    }
    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "getCatalogFallBack")
    public Respuesta getGenre(String genre){
        return new Respuesta(serieServiceClient.getSerieByGenre(genre), movieServiceClient.getMovieByGenre(genre));
    }

    public Respuesta getCatalogFallBack(String genre, Throwable t) throws Exception {
        try {
            return new Respuesta(serieRepository.findAllByGenre(genre), movieRepository.findAllByGenre(genre));
        } catch (Exception e){
            System.out.println("getCatalogFallBackException:"+ e.getMessage());
            throw new Exception("Could not delete customer");
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Respuesta{
        private List<Serie> series;
        private List<Movie> movies;
    }


}
