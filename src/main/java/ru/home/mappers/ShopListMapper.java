package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.ShopListDto;
import ru.home.models.ShopList;

@Component
public class ShopListMapper {
    @Autowired
    ModelMapper modelMapper;

    public ShopListDto entityToDto(ShopList shopList) {
        ShopListDto shopListDto = new ShopListDto();
        shopListDto.setId(shopList.getId());
        shopListDto.setOrder_id(shopList.getOrder().getId());
        shopListDto.setToy_id(shopList.getToy().getId());
        shopListDto.setCount(shopList.getCount());
        return shopListDto;
    }

    public List<ShopListDto> toShopListDtos(List<ShopList> shopLists) {
        List<ShopListDto> shopListDtos = new ArrayList<>();
        for (ShopList shopList : shopLists) {
            ShopListDto shopListDto = new ShopListDto();
            shopListDto.setId(shopList.getId());
            shopListDto.setOrder_id(shopList.getOrder().getId());
            shopListDto.setToy_id(shopList.getToy().getId());
            shopListDto.setCount(shopList.getCount());
            shopListDtos.add(shopListDto);
        }
        return shopListDtos;
    }
}
