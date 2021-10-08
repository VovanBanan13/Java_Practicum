package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.ToyDto;
import ru.home.models.Toy;


@Component
public class ToyMapper {
    @Autowired
    ModelMapper modelMapper;

    public ToyDto entityToDto(Toy toy) {
        ToyDto toyDto = new ToyDto();
        toyDto.setId(toy.getId());
        toyDto.setName(toy.getName());
        toyDto.setPrice(toy.getPrice());
        toyDto.setCategory_id(toy.getCategory().getId());
        return toyDto;
    }

    public List<ToyDto> toToyDtos(List<Toy> toys) {
        List<ToyDto> toyDtos = new ArrayList<>();
        for (Toy toy : toys) {
            ToyDto toyDto = new ToyDto();
            toyDto.setId(toy.getId());
            toyDto.setName(toy.getName());
            toyDto.setPrice(toy.getPrice());
            toyDto.setCategory_id(toy.getCategory().getId());
            toyDtos.add(toyDto);
        }
        return toyDtos;
    }
}
