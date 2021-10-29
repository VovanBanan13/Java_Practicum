package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
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
@Api(value="storage")
public class StorageController {
    private final StorageService storageService;
    private final StorageMapper storageMapper;

    @Autowired
    public StorageController(StorageService storageService, StorageMapper storageMapper) {
        this.storageService = storageService;
        this.storageMapper = storageMapper;
    }

    @ApiOperation(value = "View a list of storages")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StorageDto>> getAllStorages() {
        List<Storage> storages = storageService.findAllStorages();

        return new ResponseEntity<>(storageMapper.toStorageDtos(storages), HttpStatus.OK);
    }

    @ApiOperation(value = "View information about the storage")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StorageDto> getStorage(@PathVariable("id") Integer id) {
        Storage storage = storageService.getById(id);

        return new ResponseEntity<>(storageMapper.entityToDto(storage), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new storage")
    @PostMapping(produces = "application/json")
    public ResponseEntity<StorageDto> createStorage(@RequestBody Storage storage) {
        storageService.save(storage);

        return new ResponseEntity<>(storageMapper.entityToDto(storage), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update storage information")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StorageDto> updateStorage(@PathVariable("id") Integer id, @RequestBody Storage storage) {
        Storage changedStorage = storageService.update(id, storage);

        return new ResponseEntity<>(storageMapper.entityToDto(changedStorage), HttpStatus.OK);
    }

    @ApiOperation(value = "Remote storage")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StorageDto> deleteStorage(@PathVariable("id") Integer id) {
        Storage storage = storageService.getById(id);

        storageService.delete(id);

        return new ResponseEntity<>(storageMapper.entityToDto(storage),HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Add a toy count")
    @PostMapping(value = "/add", produces = "application/json")
    public void addCount(@RequestBody List<HashMap<String, Integer>> toyCountList) {
        storageService.addToyCount(toyCountList);
    }
}
