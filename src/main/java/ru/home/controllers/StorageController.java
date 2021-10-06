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
import ru.home.dto.StorageDto;
import ru.home.mappers.StorageMapper;
import ru.home.models.Storage;
import ru.home.services.StorageService;

@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;
    private final StorageMapper storageMapper;

    @Autowired
    public StorageController(StorageService storageService, StorageMapper storageMapper) {
        this.storageService = storageService;
        this.storageMapper = storageMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StorageDto>> getAllStorages() {
        List<Storage> storages = storageService.findAllStorages();

        return new ResponseEntity<>(storageMapper.toStorageDtos(storages), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StorageDto> getStorage(@PathVariable("id") Integer id) {
        Storage storage = storageService.getById(id);

        return new ResponseEntity<>(storageMapper.entityToDto(storage), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<StorageDto> createStorage(@RequestBody Storage storage) {
        storageService.save(storage);

        return new ResponseEntity<>(storageMapper.entityToDto(storage), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StorageDto> updateStorage(@PathVariable("id") Integer id, @RequestBody Storage storage) {
        Storage changedStorage = storageService.update(id, storage);

        return new ResponseEntity<>(storageMapper.entityToDto(changedStorage), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StorageDto> deleteStorage(@PathVariable("id") Integer id) {
        Storage storage = storageService.getById(id);

        storageService.delete(id);

        return new ResponseEntity<>(storageMapper.entityToDto(storage),HttpStatus.NO_CONTENT);
    }
}
