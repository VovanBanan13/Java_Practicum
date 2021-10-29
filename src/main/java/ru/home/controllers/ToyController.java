package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import ru.home.dto.ToyDto;
import ru.home.mappers.ToyMapper;
import ru.home.models.Toy;
import ru.home.services.ToyService;

@RestController
@RequestMapping("/toy")
@Api(value="toy")
public class ToyController {
    private final ToyService toyService;
    private final ToyMapper toyMapper;

    @Autowired
    public ToyController(ToyService toyService, ToyMapper toyMapper) {
        this.toyService = toyService;
        this.toyMapper = toyMapper;
    }

    @ApiOperation(value = "View a list of toys")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ToyDto>> getAllToys() {
        List<Toy> toys = toyService.findAllToys();

        return new ResponseEntity<>(toyMapper.toToyDtos(toys), HttpStatus.OK);
    }

    @ApiOperation(value = "View information about the toy")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ToyDto> getToy(@PathVariable("id") Integer id) {
        Toy toy = toyService.getById(id);

        return new ResponseEntity<>(toyMapper.entityToDto(toy), HttpStatus.OK);
    }

    @ApiOperation(value = "View toys by the category")
    @GetMapping(value = "/category/{id}", produces = "application/json")
    public ResponseEntity<List<ToyDto>> getAllToyByCategory(@PathVariable("id") Integer id) {
        List<Toy> toys = toyService.getAllToyByCategory(id);

        return new ResponseEntity<>(toyMapper.toToyDtos(toys), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new toy")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ToyDto> createToy(@RequestBody Toy toy) {
        toyService.save(toy);

        return new ResponseEntity<>(toyMapper.entityToDto(toy), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update toy information")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ToyDto> updateToy(@PathVariable("id") Integer id, @RequestBody Toy toy) {
        Toy changedToy = toyService.update(id, toy);

        return new ResponseEntity<>(toyMapper.entityToDto(changedToy), HttpStatus.OK);
    }

    @ApiOperation(value = "Remote toy")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ToyDto> deleteToy(@PathVariable("id") Integer id) {
        Toy toy = toyService.getById(id);

        toyService.delete(id);

        return new ResponseEntity<>(toyMapper.entityToDto(toy),HttpStatus.NO_CONTENT);
    }
}
