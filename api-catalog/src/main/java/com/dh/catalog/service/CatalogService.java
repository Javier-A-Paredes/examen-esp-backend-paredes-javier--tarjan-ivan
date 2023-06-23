package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
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
    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;

    @Autowired
    public CatalogService(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient){
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }

   public Respuesta getGenre(String genre){
        return new Respuesta(serieServiceClient.getSerieByGenre(genre), movieServiceClient.getMovieByGenre(genre));
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
