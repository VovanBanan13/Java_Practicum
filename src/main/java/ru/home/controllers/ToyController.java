package ru.home.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.dto.ToyDTO;
import ru.home.mappers.ToyMapper;
import ru.home.models.Toy;
import ru.home.services.ToyService;

@RestController
@RequestMapping("/toy")
public class ToyController {
    private final ToyService toyService;
    private final ToyMapper toyMapper;

    @Autowired
    public ToyController(ToyService toyService, ToyMapper toyMapper) {
        this.toyService = toyService;
        this.toyMapper = toyMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ToyDTO>> getAllToys() {
        List<Toy> toys = toyService.findAllToys();

        return new ResponseEntity<>(toyMapper.toToyDTOS(toys), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ToyDTO> getToy(@PathVariable("id") Integer id) {
        Toy toy = toyService.getById(id);

        return new ResponseEntity<>(toyMapper.EntityToDto(toy), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ToyDTO> createToy(@RequestBody Toy toy) {
        toyService.save(toy);

        return new ResponseEntity<>(toyMapper.EntityToDto(toy), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ToyDTO> updateToy(@PathVariable("id") Integer id, @RequestBody Toy toy) {
        Toy changedToy = toyService.update(id, toy);

        return new ResponseEntity<>(toyMapper.EntityToDto(changedToy), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ToyDTO> deleteToy(@PathVariable("id") Integer id) {
        Toy toy = toyService.getById(id);

        toyService.delete(id);

        return new ResponseEntity<>(toyMapper.EntityToDto(toy),HttpStatus.NO_CONTENT);
    }
}
