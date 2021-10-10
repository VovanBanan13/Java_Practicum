package ru.home.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.Storage;
import ru.home.models.Toy;
import ru.home.repositories.StorageRepository;
import ru.home.repositories.ToyRepository;
import ru.home.services.StorageService;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;
    private final ToyRepository toyRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository, ToyRepository toyRepository) {
        this.storageRepository = storageRepository;
        this.toyRepository = toyRepository;
    }

    @Override
    public List<Storage> findAllStorages() {
        List<Storage> storages = storageRepository.findAll();
        if(storages.isEmpty()) {
            throw new ObjectNotFoundAdvice();
        } else
            return storages;
    }

    @Override
    public Storage getById(int id) {
        return storageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public Storage getByToyName(String toyName) {
        Optional<Toy> toy = toyRepository.findByName(toyName);
        return storageRepository.findByToy(toy).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public void save(Storage storage) {
        if(storage.getToy()==null)
            throw new ObjectNotFoundAdvice();
        this.storageRepository.save(storage);
    }

    @Override
    public void delete(int id) {
        storageRepository.deleteById(id);
    }

    @Override
    public Storage update(int id, Storage storage) {
        Storage changedStorage = storageRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
        changedStorage.setToy(storage.getToy());
        changedStorage.setCount(storage.getCount());
        this.storageRepository.save(changedStorage);
        return changedStorage;
    }

    @Override
    public void addToyCount(List<HashMap<String, Integer>> toyList) {
        for (HashMap<String, Integer> toyListMap : toyList) {
            for (Map.Entry<String, Integer> toyCount : toyListMap.entrySet()) {
                Storage storage = getByToyName(toyCount.getKey());
                storage.setCount(storage.getCount() + toyCount.getValue());
                this.storageRepository.save(storage);
            }
        }
    }
}
