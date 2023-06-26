package com.dh.catalog.client;

import com.dh.catalog.model.serie.Serie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="serie-service")
public interface SerieServiceClient {

    @Retry(name = "retryCatalog")
    @CircuitBreaker(name = "clientCatalog", fallbackMethod = "createCatalogFallBack")
    @GetMapping("/api/v1/series/{genre}")
    List<Serie> getSerieByGenre(@PathVariable (value = "genre") String genre);




}
