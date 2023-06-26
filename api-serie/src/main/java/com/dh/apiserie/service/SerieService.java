package com.dh.apiserie.service;

import com.dh.apiserie.event.SaveSerieEventProducer;
import com.dh.apiserie.model.Serie;
import com.dh.apiserie.repository.SerieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {

    private final SerieRepository repository;
    private final SaveSerieEventProducer saveSerieEventProducer;



    public SerieService(SerieRepository repository, SaveSerieEventProducer saveSerieEventProducer) {
        this.repository = repository;
        this.saveSerieEventProducer = saveSerieEventProducer;
    }



    public List<Serie> getSeriesBygGenre(String genre) {
        return repository.findAllByGenre(genre);
    }

    public Serie createSerie(Serie serie) {
        saveSerieEventProducer.publishFinalizarCursoEvent(serie);
        return repository.save(serie);
    }

    public List<Serie> findAll(){
        return repository.findAll();
    }
}
