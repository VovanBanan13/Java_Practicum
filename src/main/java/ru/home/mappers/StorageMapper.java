package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.StorageDTO;
import ru.home.models.Storage;

@Component
public class StorageMapper {
    @Autowired
    ModelMapper modelMapper;

    public StorageDTO EntityToDto(Storage storage) {
        StorageDTO storageDTO = new StorageDTO();
        storageDTO.setId(storage.getId());
        storageDTO.setToy_id(storage.getToy().getId());
        storageDTO.setCount(storage.getCount());
        return storageDTO;
    }

    public List<StorageDTO> toStorageDTOS(List<Storage> storages) {
        List<StorageDTO> storageDTOS = new ArrayList<>();
        for (Storage storage : storages) {
            StorageDTO storageDTO = new StorageDTO();
            storageDTO.setId(storage.getId());
            storageDTO.setToy_id(storage.getToy().getId());
            storageDTO.setCount(storage.getCount());
            storageDTOS.add(storageDTO);
        }
        return storageDTOS;
    }
}
