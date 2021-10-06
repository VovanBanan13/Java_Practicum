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
import ru.home.dto.ShopListDTO;
import ru.home.mappers.ShopListMapper;
import ru.home.models.ShopList;
import ru.home.services.ShopListService;

@RestController
@RequestMapping("/shop_list")
public class ShopListController {
    private final ShopListService shopListService;
    private final ShopListMapper shopListMapper;

    @Autowired
    public ShopListController(ShopListService shopListService, ShopListMapper shopListMapper) {
        this.shopListService = shopListService;
        this.shopListMapper = shopListMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ShopListDTO>> getAllShopLists() {
        List<ShopList> shopLists = shopListService.findAllShopLists();

        return new ResponseEntity<>(shopListMapper.toShopListDTOS(shopLists), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ShopListDTO> getShopList(@PathVariable("id") Integer id) {
        ShopList shopList = shopListService.getById(id);

        return new ResponseEntity<>(shopListMapper.EntityToDto(shopList), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ShopListDTO> createShopList(@RequestBody ShopList shopList) {
        shopListService.save(shopList);

        return new ResponseEntity<>(shopListMapper.EntityToDto(shopList), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ShopListDTO> updateShopList(@PathVariable("id") Integer id, @RequestBody ShopList shopList) {
        ShopList changedShopList = shopListService.update(id, shopList);

        return new ResponseEntity<>(shopListMapper.EntityToDto(changedShopList), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ShopListDTO> deleteShopList(@PathVariable("id") Integer id) {
        ShopList shopList = shopListService.getById(id);

        shopListService.delete(id);

        return new ResponseEntity<>(shopListMapper.EntityToDto(shopList),HttpStatus.NO_CONTENT);
    }
}
