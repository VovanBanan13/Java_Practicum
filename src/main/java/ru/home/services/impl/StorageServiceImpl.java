package ru.home.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.Storage;
import ru.home.repositories.StorageRepository;
import ru.home.services.StorageService;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    private final StorageRepository storageRepository;

    @Autowired
    public StorageServiceImpl(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
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
    public void save(Storage storage) {
        if(storage.getToy()==null || storage.getCount()==0)
            throw new ObjectNotFoundAdvice();
        this.storageRepository.save(storage);
    }

    @Override
    public void delete(int id) {
        storageRepository.deleteById(id);
    }
}
