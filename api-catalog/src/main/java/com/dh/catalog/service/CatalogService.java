package com.dh.catalog.service;

import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import com.netflix.discovery.converters.Auto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;

    @Autowired
    public CatalogService(MovieRepository movieRepository, SerieRepository serieRepository){
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
    }

   public Respuesta getGenre(String genre){
        return new Respuesta(serieRepository.findAllByGenre(genre), movieRepository.findAllByGenre(genre));
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
