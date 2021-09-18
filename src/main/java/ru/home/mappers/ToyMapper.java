package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.ToyDTO;
import ru.home.models.Toy;


@Component
public class ToyMapper {
    @Autowired
    ModelMapper modelMapper;

    public ToyDTO EntityToDto(Toy toy) {
        ToyDTO toyDTO = new ToyDTO();
        toyDTO.setId(toy.getId());
        toyDTO.setName(toy.getName());
        toyDTO.setPrice(toy.getPrice());
        toyDTO.setCategory_id(toy.getCategory().getId());
        return toyDTO;
    }

    public List<ToyDTO> toToyDTOS(List<Toy> toys) {
        List<ToyDTO> toyDTOS = new ArrayList<>();
        for (Toy toy : toys) {
            ToyDTO toyDTO = new ToyDTO();
            toyDTO.setId(toy.getId());
            toyDTO.setName(toy.getName());
            toyDTO.setPrice(toy.getPrice());
            toyDTO.setCategory_id(toy.getCategory().getId());
            toyDTOS.add(toyDTO);
        }
        return toyDTOS;
    }
}
