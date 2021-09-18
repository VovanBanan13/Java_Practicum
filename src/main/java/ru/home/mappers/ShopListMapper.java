package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.ShopListDTO;
import ru.home.models.ShopList;

@Component
public class ShopListMapper {
    @Autowired
    ModelMapper modelMapper;

    public ShopListDTO EntityToDto(ShopList shopList) {
        ShopListDTO shopListDTO = new ShopListDTO();
        shopListDTO.setId(shopList.getId());
        shopListDTO.setOrder_id(shopList.getOrder().getId());
        shopListDTO.setToy_id(shopList.getToy().getId());
        shopListDTO.setCount(shopList.getCount());
        return shopListDTO;
    }

    public List<ShopListDTO> toShopListDTOS(List<ShopList> shopLists) {
        List<ShopListDTO> shopListDTOS = new ArrayList<>();
        for (ShopList shopList : shopLists) {
            ShopListDTO shopListDTO = new ShopListDTO();
            shopListDTO.setId(shopList.getId());
            shopListDTO.setOrder_id(shopList.getOrder().getId());
            shopListDTO.setToy_id(shopList.getToy().getId());
            shopListDTO.setCount(shopList.getCount());
            shopListDTOS.add(shopListDTO);
        }
        return shopListDTOS;
    }
}
