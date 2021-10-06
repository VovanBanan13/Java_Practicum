package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.StorageDto;
import ru.home.models.Storage;

@Component
public class StorageMapper {
    @Autowired
    ModelMapper modelMapper;

    public StorageDto entityToDto(Storage storage) {
        StorageDto storageDto = new StorageDto();
        storageDto.setId(storage.getId());
        storageDto.setToy_id(storage.getToy().getId());
        storageDto.setCount(storage.getCount());
        return storageDto;
    }

    public List<StorageDto> toStorageDtos(List<Storage> storages) {
        List<StorageDto> storageDtos = new ArrayList<>();
        for (Storage storage : storages) {
            StorageDto storageDto = new StorageDto();
            storageDto.setId(storage.getId());
            storageDto.setToy_id(storage.getToy().getId());
            storageDto.setCount(storage.getCount());
            storageDtos.add(storageDto);
        }
        return storageDtos;
    }
}
