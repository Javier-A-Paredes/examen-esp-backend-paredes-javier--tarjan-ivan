package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.service.CatalogService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;
	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/movie/{genre}")
	ResponseEntity<List<Movie>> getGenreMovie(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getMovieByGenre(genre));
	}
	@GetMapping("/serie/{genre}")
	ResponseEntity<List<Serie>> getGenreSerie(@PathVariable String genre){
		return ResponseEntity.ok(catalogService.getSerieByGenre(genre));
	};

	@GetMapping("/{genre}")
	ResponseEntity<CatalogService.Respuesta> getGenre(@PathVariable String genre){
		return ResponseEntity.ok(catalogService.getGenre(genre));
	}

}
